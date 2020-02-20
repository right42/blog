package me.right.study.common.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.right.study.common.domain.FileData;
import me.right.study.common.repository.FileRepository;
import me.right.study.common.util.Utils;
import me.right.study.common.util.WebUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static me.right.study.common.util.WebUtil.getSessionAttribute;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FileService {

    private static final String FILE_UPLOAD_PATH = "C:\\Users\\ASUS\\Desktop\\개인\\project\\blog\\src\\main\\resources\\static\\upload\\";

    private static final String TEMP_FILE_LIST = "tempFiles";

    private final FileRepository fileRepository;

    @Transactional(rollbackFor = Exception.class)
    public String upload(MultipartFile file) throws IOException {

        Long savedId = save(file);
        FileData fileData = fileRepository.getOne(savedId);

        addFileId(savedId);

        // TODO local Storage -> AWS S3
        return "/upload/" + fileData.getFileName();
    }

    private Long save(MultipartFile file) throws IOException {

        FileData fileData = FileData.createFileData(file.getOriginalFilename(), FILE_UPLOAD_PATH);
        file.transferTo(Path.of(FILE_UPLOAD_PATH + fileData.getFileName()));

        return fileRepository.save(fileData).getId();
    }

    private void addFileId(Long id){
        List<Long> tempFiles = WebUtil.getSessionAttribute(TEMP_FILE_LIST);

        if (Utils.isNotNullAndNotEmpty(tempFiles)) {
            tempFiles.add(id);
        } else {
            WebUtil.setSessionAttribute(TEMP_FILE_LIST, Arrays.asList(id));
        }
    }

    private void removeTempFiles(){
        List<Long> tempFiles = getSessionAttribute(TEMP_FILE_LIST);

        List<FileData> allById = fileRepository.findAllById(tempFiles);
        allById.forEach(e -> {
            String fileFullName = e.getFileFullName();

            try {
                Files.delete(Path.of(fileFullName));
            } catch (IOException ex) {
                log.error("File Delete Fail ==> " + ex.getMessage());
            }
        });

    }
}

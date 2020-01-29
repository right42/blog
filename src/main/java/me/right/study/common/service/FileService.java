package me.right.study.common.service;

import lombok.RequiredArgsConstructor;
import me.right.study.common.domain.File;
import me.right.study.common.repository.FileRepository;
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

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FileService {

    private static final String FILE_UPLOAD_PATH = "C:\\Users\\ASUS\\Desktop\\개인\\project\\image";

    private final FileRepository fileRepository;

    @Transactional(rollbackFor = IOException.class)
    public String save(MultipartFile file) throws IOException {

        File fileData = File.createFileData(file.getOriginalFilename(), FileService.FILE_UPLOAD_PATH);
        file.transferTo(Path.of(FILE_UPLOAD_PATH + fileData.getFileName()));

        Long savedId = fileRepository.save(fileData).getId();
        addFileId(savedId);

        return fileData.getFilePath() + java.io.File.separator + fileData.getFileName();
    }

    private void addFileId(Long id){
        if (Objects.isNull(WebUtil.getSessionAttribute("uploadFiles"))){
            WebUtil.setSessionAttribute("uploadFiles", Arrays.asList(id));
        } else {
            List<Long> uploadFiles = WebUtil.<List>getSessionAttribute("uploadFiles");
            uploadFiles.add(id);
        }
    }


}

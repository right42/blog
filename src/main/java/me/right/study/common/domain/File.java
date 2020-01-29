package me.right.study.common.domain;

import lombok.*;
import me.right.study.common.service.FileService;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String fileOriginalName;

    private String fileName;

    private String filePath;


    public static File createFileData(String fileName, String filePath) {
        File file = new File();
        file.fileOriginalName = fileName;
        file.fileName = System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf('.'));
        file.filePath = filePath;

        return file;
    }

    public static void main(String[] args) {
        System.out.println("test.jpg".substring("test.jpg".lastIndexOf('.')));
    }
}

package me.right.study.common.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.File;

import static java.io.File.*;

@Entity
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileData extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String fileOriginalName;

    private String fileName;

    private String filePath;

    private String fileFullName;

    public static FileData createFileData(String fileName, String filePath) {
        FileData fileData = new FileData();
        fileData.fileOriginalName = fileName;
        fileData.fileName = System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf('.'));
        fileData.filePath = filePath;
        fileData.fileFullName = filePath + fileData.fileName;

        return fileData;
    }

    public static void main(String[] args) {
        System.out.println("test.jpg".substring("test.jpg".lastIndexOf('.')));
    }
}

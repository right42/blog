package me.right.study.common.web;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.right.study.common.service.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/files")
    public CKEditorResponse upload(@RequestParam("upload") MultipartFile file){

        try {
            String filePath = fileService.upload(file);

            return new CKEditorResponse(filePath);
        } catch (IOException e) {
            return new CKEditorResponse();
        }
    }

    @Data
    private class CKEditorResponse {

        private String uri;

        private String error;

        public CKEditorResponse() {}

        public CKEditorResponse(String filePath) {
            this.uri = filePath;
        }
    }
}

package me.right.study.common.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FileServiceTest {

    @Autowired
    ResourceLoader resourceLoader;

    @Test
    public void resourcePath() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("/");
        System.out.println(classPathResource.getFile());

    }

}
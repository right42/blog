package me.right.study.tag.web;

import me.right.study.common.web.ErrorControllerAdvisor;
import me.right.study.tag.exception.TagDuplicationException;
import me.right.study.tag.service.TagService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(controllers = {TagController.class, ErrorControllerAdvisor.class})
class TagControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TagService tagService;

    @Test
    public void createTag(){

    }

    @Test
    public void createTag_with_tagDuplication() throws Exception {
        when(tagService.save(null)).thenThrow(new TagDuplicationException("해당 태그가 이미 있습니다."));

        ResultActions resultActions = mockMvc.perform(
                post("/api/v1/tags")
                        .param("name", "JPA1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
        )
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value("해당 태그가 이미 있습니다."));

    }

}
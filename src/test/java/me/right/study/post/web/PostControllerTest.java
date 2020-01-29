package me.right.study.post.web;

import me.right.study.post.domain.dto.PostRequestDto;
import me.right.study.post.service.PostService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PostService postService;

    @Test
    public void post_create() throws Exception {
        var postDto = new PostRequestDto();
        postDto.setContent("content");
        postDto.setTitle("title");

        when(postService.save(postDto)).thenReturn(1l);

        mockMvc.perform(
                post("/posts")
                .param("content", postDto.getContent())
                .param("title", postDto.getTitle())
            )
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/"))

        ;

    }

}
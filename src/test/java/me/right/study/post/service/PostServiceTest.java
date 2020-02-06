package me.right.study.post.service;

import me.right.study.post.domain.Post;
import me.right.study.post.domain.dto.PostRequestDto;
import me.right.study.post.domain.dto.PostResponseDto;
import me.right.study.post.repository.PostRepository;
import me.right.study.tag.domain.Tag;
import me.right.study.tag.dto.TagRequestDto;
import me.right.study.tag.repository.TagRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class PostServiceTest {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    @Test
    @Transactional
    public void save(){

        PostRequestDto dto = new PostRequestDto();
        dto.setTitle("title");
        dto.setWriter("writer");
        dto.setContent("test");
        dto.setTagIds(List.of(1l, 2l));

        Long id = postService.save(dto);

        Post post = postRepository.findById(id).get();
        assertThat(post).isNotNull();
        post.getPostTags().get(0);
    }

}
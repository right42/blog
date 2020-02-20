package me.right.study.post.service;

import me.right.study.post.domain.Post;
import me.right.study.post.dto.PostRequestDto;
import me.right.study.post.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
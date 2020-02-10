package me.right.study.post.repository;

import me.right.study.post.domain.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class SimpleCustomPostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Test
    public void querydsl_findAll(){

        List<Post> result = postRepository.findAllWithQueryDsl();

        assertThat(result.size()).isEqualTo(0);
    }

    @Test
    public void findAllByTag(){

        postRepository.findAllByTag("JPA");

    }
}
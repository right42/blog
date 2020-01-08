package me.right.study;

import lombok.RequiredArgsConstructor;
import me.right.study.post.domain.Post;
import me.right.study.post.repository.PostRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitData implements ApplicationRunner {

    private final PostRepository postRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {

        for (int i = 0; i < 11; i++) {
            postRepository.save(
                    Post.builder()
                            .title("title" + i)
                            .content("content" + i)
                            .writer("test" + i)
                            .build()
            );
        }

    }
}

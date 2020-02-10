package me.right.study;

import lombok.RequiredArgsConstructor;
import me.right.study.post.domain.Post;
import me.right.study.post.repository.PostRepository;
import me.right.study.tag.domain.PostTag;
import me.right.study.tag.domain.Tag;
import me.right.study.tag.repository.TagRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class InitData implements ApplicationRunner {

    private final PostRepository postRepository;

    private final TagRepository tagRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {

        for (int i = 0; i < 11; i++) {
            Post savedPost = postRepository.save(
                                Post.builder()
                                        .title("title" + i)
                                        .content("content" + i)
                                        .writer("test" + i)
                                        .build()
                        );

            Tag savedTag = tagRepository.save(Tag.builder()
                    .name("JPA" + i)
                    .build()
            );

            PostTag.linkPostAndTag(savedPost, Arrays.asList(savedTag));
        }
    }
}

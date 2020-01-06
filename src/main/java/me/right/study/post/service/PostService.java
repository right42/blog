package me.right.study.post.service;

import lombok.RequiredArgsConstructor;
import me.right.study.post.domain.Post;
import me.right.study.post.domain.dto.PostRequestDto;
import me.right.study.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    public List<Post> findAll(){
        return postRepository.findAll();
    }

    @Transactional
    public Long save(PostRequestDto postRequestDto) {
        return postRepository.save(postRequestDto.toEntity()).getId();
    }
}

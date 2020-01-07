package me.right.study.post.service;

import lombok.RequiredArgsConstructor;
import me.right.study.post.domain.Post;
import me.right.study.post.domain.dto.PostRequestDto;
import me.right.study.post.domain.dto.PostResponseDto;
import me.right.study.post.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    private List<Post> findAll(){
        return postRepository.findAll();
    }

    public Page<PostResponseDto> findAll(Pageable page){
        return postRepository.findAll(page).map(PostResponseDto::new);
    }

    @Transactional
    public Long save(PostRequestDto postRequestDto) {
        return postRepository.save(postRequestDto.toEntity()).getId();
    }
}

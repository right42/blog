package me.right.study.post.service;

import lombok.RequiredArgsConstructor;
import me.right.study.post.domain.Post;
import me.right.study.post.repository.dto.PostArchiveDto;
import me.right.study.post.dto.PostRequestDto;
import me.right.study.post.dto.PostResponseDto;
import me.right.study.post.repository.PostRepository;
import me.right.study.tag.domain.PostTag;
import me.right.study.tag.domain.Tag;
import me.right.study.tag.repository.TagRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    private final TagRepository tagRepository;

    public List<PostResponseDto> findAll(){
        return postRepository.findAll()
                    .stream()
                    .map(PostResponseDto::new)
                    .collect(Collectors.toList())
                ;
    }

    public Page<PostResponseDto> findAll(Pageable page){
        return postRepository
                .findAll(page).map(PostResponseDto::new);
    }

    @Transactional
    public Long save(PostRequestDto postRequestDto) {
        postRequestDto.setWriter("right");

        Post post = postRequestDto.toEntity();
        List<Tag> tags = tagRepository.findAllById(postRequestDto.getTagIds());
        PostTag.linkPostAndTag(post, tags);

        return postRepository.save(post).getId();
    }

    public PostResponseDto findOne(Long id) {
        var findPost = postRepository.findById(id).orElseThrow();

        return new PostResponseDto(findPost);
    }

    public List<PostArchiveDto> findAllGroupingYear(){
        List<Post> findPosts = postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdDate"));

        List<PostArchiveDto> archives = new ArrayList<>();
        findPosts.stream()
                .collect(Collectors.groupingBy((post) -> post.getCreatedDate().getYear()))
                .forEach((year, posts) -> {
                    archives.add(new PostArchiveDto(year, posts));
                });

        return archives;
    }

}

package me.right.study.post.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.right.study.post.domain.Post;
import me.right.study.post.dto.PostResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor
public class PostArchiveDto {

    private int year;

    private List<PostResponseDto> posts;

    public PostArchiveDto(int year, List<Post> postList) {
        this.year = year;
        posts = postList.stream()
                    .map(post -> PostResponseDto.builder()
                                     .id(post.getId())
                                     .title(post.getTitle())
                                     .createdDate(post.getCreatedDate())
                                     .build()
                    )
                    .collect(Collectors.toList());
    }

}

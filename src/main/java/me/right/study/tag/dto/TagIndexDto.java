package me.right.study.tag.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.right.study.post.dto.PostResponseDto;
import me.right.study.tag.repository.dto.TagPostDto;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor
public class TagIndexDto {

    private String tagName;

    private List<PostResponseDto> posts;

    public TagIndexDto(String tagName, List<TagPostDto> postTags) {
        this.tagName = tagName;
        this.posts = postTags.stream()
                        .map(postTag ->
                                PostResponseDto.builder()
                                    .id(postTag.getPostId())
                                    .title(postTag.getPostTitle())
                                    .build()
                        )
                        .collect(Collectors.toList());
    }

}

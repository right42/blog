package me.right.study.post.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.right.study.post.domain.Post;
import me.right.study.tag.dto.TagResponseDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Setter @Getter @Builder
@AllArgsConstructor
public class PostResponseDto {

    private Long id;

    private String title;

    private String content;

    private String writer;

    private LocalDateTime createdDate;

    private List<TagResponseDto> tags;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.content = post.getContent();
        this.title = post.getTitle();
        this.writer = post.getWriter();
        this.createdDate = post.getCreatedDate();
        this.tags = post.getPostTags().stream()
                        .map(e -> new TagResponseDto(e.getTag()))
                        .collect(Collectors.toList());

    }

    public String getCreatedDate(){
        return createdDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();
    }

}

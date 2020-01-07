package me.right.study.post.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.right.study.post.domain.Post;

import java.time.LocalDateTime;

@Setter @Getter @Builder
@AllArgsConstructor
public class PostResponseDto {

    private String title;

    private String writer;

    private LocalDateTime createdDate;

    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.writer = post.getWriter();
        this.createdDate = post.getCreatedDate();
    }

}

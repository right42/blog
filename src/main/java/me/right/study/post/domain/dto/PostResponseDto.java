package me.right.study.post.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.right.study.post.domain.Post;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter @Getter @Builder
@AllArgsConstructor
public class PostResponseDto {

    private Long id;

    private String title;

    private String content;

    private String writer;

    private LocalDateTime createdDate;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.content = post.getContent();
        this.title = post.getTitle();
        this.writer = post.getWriter();
        this.createdDate = post.getCreatedDate();
    }

    public String getCreatedDate(){
        return createdDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();
    }

}

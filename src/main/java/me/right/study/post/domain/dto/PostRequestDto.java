package me.right.study.post.domain.dto;

import lombok.Getter;
import lombok.Setter;
import me.right.study.post.domain.Post;
import me.right.study.post.domain.PostCategory;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class PostRequestDto {

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    private String writer;

    private PostCategory postCategory;

    public Post toEntity() {
        return Post.builder()
                    .writer(this.writer)
                    .content(this.content)
                    .title(this.title)
                    .build();
    }
}

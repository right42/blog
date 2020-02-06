package me.right.study.post.domain.dto;

import lombok.Getter;
import lombok.Setter;
import me.right.study.post.domain.Post;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter @Setter
public class PostRequestDto {

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    private String writer;

    private List<Long> tagIds;

    public Post toEntity() {
        return Post.builder()
                    .writer(this.writer)
                    .content(this.content)
                    .title(this.title)
                    .build();
    }
}

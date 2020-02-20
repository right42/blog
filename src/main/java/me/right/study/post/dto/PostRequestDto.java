package me.right.study.post.dto;

import lombok.Getter;
import lombok.Setter;
import me.right.study.post.domain.Post;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter @Setter
public class PostRequestDto {

    @Size(max = 20, min = 3, message = "제목은 3글자 이상 20글자 이하입니다.")
    private String title;

    @NotEmpty(message = "해당 내용은 빈칸이면 안됩니다.")
    private String content;

    private String writer;

    @NotEmpty
    private List<Long> tagIds;

    public Post toEntity() {
        return Post.builder()
                    .writer(this.writer)
                    .content(this.content)
                    .title(this.title)
                    .build();
    }
}

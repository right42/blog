package me.right.study.post.domain;

import lombok.*;
import me.right.study.comment.domain.Comment;
import me.right.study.common.domain.BaseTimeEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

@Entity
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;

    private String title;

    private String writer;

    @Lob
    private String content;

    @ManyToOne(fetch = LAZY)
    private PostCategory postCategory;

}

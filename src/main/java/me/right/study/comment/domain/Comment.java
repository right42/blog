package me.right.study.comment.domain;

import lombok.*;
import me.right.study.common.domain.BaseTimeEntity;
import me.right.study.post.domain.Post;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Comment extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;

    private String content;

    private String password;

    @ManyToOne(fetch = LAZY)
    private Post post;

}

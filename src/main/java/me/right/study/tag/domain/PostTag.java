package me.right.study.tag.domain;

import lombok.*;
import me.right.study.common.domain.BaseTimeEntity;
import me.right.study.post.domain.Post;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostTag extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Post post;

    @ManyToOne(fetch = LAZY)
    private Tag tag;


}


package me.right.study.post.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostCategory {

    @Id @GeneratedValue
    private Long id;

    private String title;

    private String etc;

    @ManyToOne(fetch = FetchType.LAZY)
    private PostCategory parents;

}

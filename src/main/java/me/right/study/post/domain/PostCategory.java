package me.right.study.post.domain;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter @Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class PostCategory {

    @Id @GeneratedValue
    private Long id;

    private String title;

    private String etc;

    @ManyToOne(fetch = LAZY, cascade = ALL)
    private PostCategory parents;

}

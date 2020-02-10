package me.right.study.post.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.right.study.common.domain.BaseTimeEntity;
import me.right.study.tag.domain.PostTag;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;

    private String title;

    private String writer;

    @Lob
    private String content;

    @OneToMany(fetch = LAZY, cascade = ALL, mappedBy = "post")
    private List<PostTag> postTags = new ArrayList<>();


    @Builder
    public Post(Long id, String title, String writer, String content) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.content = content;
    }

    public void addPostTag(PostTag postTag){
        this.postTags.add(postTag);
        postTag.setPost(this);
    }
}


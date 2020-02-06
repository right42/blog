package me.right.study.tag.domain;

import lombok.*;
import me.right.study.common.domain.BaseTimeEntity;
import me.right.study.post.domain.Post;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostTag extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    private Post post;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    private Tag tag;

    public static void linkPostAndTag(Post post, List<Tag> tags){
        List<PostTag> postTags = new ArrayList<>();

        tags.forEach((e) -> {
            PostTag postTag = new PostTag();
            postTag.tag = e;

            postTag.post = post;
            post.addPostTag(postTag);

            postTags.add(postTag);
        });


    }

    public void setPost(Post post) {
        this.post = post;
    }
}


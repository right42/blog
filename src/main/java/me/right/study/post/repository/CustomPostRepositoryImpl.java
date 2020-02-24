package me.right.study.post.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import me.right.study.post.domain.Post;
import me.right.study.post.domain.QPost;
import me.right.study.tag.domain.QPostTag;
import me.right.study.tag.domain.QTag;

import javax.persistence.EntityManager;
import java.util.List;

public class CustomPostRepositoryImpl implements CustomPostRepository<Post, Long> {

    private final JPAQueryFactory query;

    public CustomPostRepositoryImpl(EntityManager em){
        query = new JPAQueryFactory(em);
    }

    @Override
    public List<Post> findAllWithQueryDsl() {
        QPost post = QPost.post;

        return query.
            selectFrom(post)
                    .fetch();
    }

    @Override
    public List<Post> findAllByTagName(String tagName){
        QPost post = QPost.post;
        QTag tag = QTag.tag;
        QPostTag postTag = QPostTag.postTag;

        return query
            .selectFrom(post)
            .innerJoin(postTag)
                .on(post.id.eq(postTag.post.id))
            .innerJoin(tag)
                .on(tag.id.eq(postTag.tag.id))
            .where(tag.name.eq(tagName))
            .fetch();

    }

}

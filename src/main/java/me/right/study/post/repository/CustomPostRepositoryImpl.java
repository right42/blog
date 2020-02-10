package me.right.study.post.repository;

import com.querydsl.core.annotations.QueryProjection;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import lombok.Data;
import me.right.study.post.domain.Post;
import me.right.study.post.domain.QPost;
import me.right.study.tag.domain.QPostTag;
import me.right.study.tag.domain.QTag;
import org.hibernate.criterion.Projection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Collections;
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
                .on(post.id.eq(postTag.post.id)).fetchJoin()
            .innerJoin(tag).fetchJoin()
                .on(tag.id.eq(postTag.tag.id)).fetchJoin()
            .where(tag.name.eq(tagName))
            .fetch();
    }

}

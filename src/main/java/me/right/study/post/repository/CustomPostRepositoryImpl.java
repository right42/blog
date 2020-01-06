package me.right.study.post.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import me.right.study.post.domain.Post;
import me.right.study.post.domain.QPost;
import org.springframework.stereotype.Repository;

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
}

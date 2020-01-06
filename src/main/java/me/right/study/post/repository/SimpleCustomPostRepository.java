package me.right.study.post.repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import me.right.study.post.domain.Post;
import me.right.study.post.domain.QPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static me.right.study.post.domain.QPost.*;

public class SimpleCustomPostRepository extends QuerydslRepositorySupport implements CustomPostRepository<Post, Long> {

    public SimpleCustomPostRepository(Class<?> domainClass) {
        super(domainClass);
    }

    @Override
    public List<Post> findAllWithQueryDsl() {
        QPost post = QPost.post;

        List<Post> result = from(post)
                                .fetch();

        return result;
    }
}

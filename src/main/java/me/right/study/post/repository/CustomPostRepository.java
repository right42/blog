package me.right.study.post.repository;

import me.right.study.post.domain.Post;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface CustomPostRepository<T, Id> {

    List<T> findAllWithQueryDsl();

    List<Post> findAllByTagName(String tagName);
}

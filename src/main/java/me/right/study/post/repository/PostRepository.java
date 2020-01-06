package me.right.study.post.repository;

import me.right.study.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>, CustomPostRepository<Post, Long> {
}

package me.right.study.tag.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import me.right.study.post.domain.QPost;
import me.right.study.tag.domain.QPostTag;
import me.right.study.tag.domain.QTag;
import me.right.study.tag.repository.dto.TagPostDto;

import javax.persistence.EntityManager;
import java.util.List;

public class CustomTagRepositoryImpl implements CustomTagRepository {

    private final JPAQueryFactory query;

    public CustomTagRepositoryImpl(EntityManager entityManager) {
        query = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<TagPostDto> findAllWithPost() {
        QTag tag = QTag.tag;
        QPost post = QPost.post;
        QPostTag postTag = QPostTag.postTag;

        return query
                .select(Projections.constructor(TagPostDto.class, tag.name, post.id, post.title))
                .from(tag)
                .innerJoin(postTag)
                    .on(tag.id.eq(postTag.tag.id))
                .innerJoin(post)
                    .on(postTag.post.id.eq(post.id))
                .orderBy(tag.name.asc())
                .fetch();
    }
}

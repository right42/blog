package me.right.study.tag.repository;


import me.right.study.tag.domain.Tag;
import me.right.study.tag.dto.TagResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long>, CustomTagRepository{

    List<Tag> findByNameIgnoreCaseContaining(String keyword);

    Optional<Tag> findByName(String name);
}

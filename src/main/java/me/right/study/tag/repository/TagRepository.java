package me.right.study.tag.repository;


import me.right.study.tag.domain.Tag;
import me.right.study.tag.dto.TagResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long>{

    List<Tag> findByNameIgnoreCaseContaining(String keyword);
}

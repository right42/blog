package me.right.study.tag.service;

import me.right.study.tag.domain.Tag;
import me.right.study.tag.dto.TagRequestDto;
import me.right.study.tag.exception.TagDuplicationException;
import me.right.study.tag.repository.TagRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TagServiceTest {

    @Autowired
    TagService tagService;

    @Autowired
    TagRepository tagRepository;

    @Test
    public void save(){
        TagRequestDto dto = TagRequestDto.builder()
                .name("JPA1")
                .build();

        Long id = tagService.save(dto);

        Tag tag = tagRepository.findById(id).get();
        assertThat(tag.getName()).isEqualTo(dto.getName());

    }

    @Test
    public void save_duplication_name(){
        TagRequestDto dto = TagRequestDto.builder()
                                .name("JPA1")
                                .build();

        TagDuplicationException exception = assertThrows(TagDuplicationException.class, () -> {
            tagService.save(dto);
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isEqualTo("해당 태그가 이미 있습니다.");

    }

}
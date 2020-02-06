package me.right.study.tag.service;


import lombok.RequiredArgsConstructor;
import me.right.study.tag.dto.TagRequestDto;
import me.right.study.tag.dto.TagResponseDto;
import me.right.study.tag.repository.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    @Transactional
    public Long save(TagRequestDto dto){
        return tagRepository.save(dto.toEntity()).getId();
    }


    public List<TagResponseDto> findAll(String keyword){

        return tagRepository.findByNameIgnoreCaseContaining(keyword)
                    .stream()
                    .map(TagResponseDto::new)
                    .collect(Collectors.toList());
    }

}

package me.right.study.tag.service;


import lombok.RequiredArgsConstructor;
import me.right.study.tag.dto.TagIndexDto;
import me.right.study.tag.dto.TagRequestDto;
import me.right.study.tag.dto.TagResponseDto;
import me.right.study.tag.repository.TagRepository;
import me.right.study.tag.repository.dto.TagPostDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public List<TagResponseDto> findAll(){
        return tagRepository.findAll()
                    .stream()
                    .map(TagResponseDto::new)
                    .collect(Collectors.toList())
                ;
    }

    @Transactional
    public Long save(TagRequestDto dto){
        return tagRepository.save(dto.toEntity()).getId();
    }

    public List<TagResponseDto> findAllByName(String keyword){

        return tagRepository.findByNameIgnoreCaseContaining(keyword)
                    .stream()
                    .map(TagResponseDto::new)
                    .collect(Collectors.toList());
    }

    public List<TagIndexDto> findAllWithPost() {
        List<TagIndexDto> result = new ArrayList<>();

        tagRepository.findAllWithPost().stream()
                .collect(groupingBy(TagPostDto::getTagName))
                .forEach((s, tagPostDto) -> result.add(new TagIndexDto(s, tagPostDto)));

        return result;
    }
}

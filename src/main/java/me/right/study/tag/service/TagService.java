package me.right.study.tag.service;


import lombok.RequiredArgsConstructor;
import me.right.study.tag.dto.TagIndexDto;
import me.right.study.tag.dto.TagRequestDto;
import me.right.study.tag.dto.TagResponseDto;
import me.right.study.tag.exception.TagDuplicationException;
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
    public Long save(TagRequestDto dto) {
        duplicationTag(dto.getName());

        return tagRepository.save(dto.toEntity()).getId();
    }

    private void duplicationTag(String tagName) throws TagDuplicationException {
        if (!tagRepository.findByName(tagName).isEmpty()){
            throw new TagDuplicationException("해당 태그가 이미 있습니다.");
        }
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

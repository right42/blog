package me.right.study.tag.repository;

import me.right.study.tag.repository.dto.TagPostDto;

import java.util.List;

public interface CustomTagRepository {

    List<TagPostDto> findAllWithPost();

}

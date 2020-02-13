package me.right.study.tag.repository.dto;

import lombok.Data;

@Data
public class TagPostDto {

    private String tagName;

    private Long postId;

    private String postTitle;

    public TagPostDto(String tagName, Long postId, String postTitle) {
        this.tagName = tagName;
        this.postId = postId;
        this.postTitle = postTitle;
    }
}

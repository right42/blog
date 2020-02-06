package me.right.study.tag.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.right.study.tag.domain.Tag;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagResponseDto {

    private Long id;

    private String name;

    public TagResponseDto(Tag tag) {
        this.id = tag.getId();
        this.name = tag.getName();
    }
}

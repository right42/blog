package me.right.study.tag.dto;


import lombok.*;
import me.right.study.tag.domain.Tag;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TagRequestDto {

    @NotBlank
    private String name;

    public Tag toEntity() {
        return Tag.builder()
                .name(name)
                .build();
    }
}

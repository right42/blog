package me.right.study.tag.domain;


import lombok.*;
import me.right.study.common.domain.BaseTimeEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Tag extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
}

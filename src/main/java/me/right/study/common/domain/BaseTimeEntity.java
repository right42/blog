package me.right.study.common.domain;

import lombok.Getter;
import org.apache.tomcat.jni.Local;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseTimeEntity {

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    @PrePersist
    public void prePersist(){

        createdDate = LocalDateTime.now();

    }

    @PreUpdate
    public void preUpdate(){
        modifiedDate = LocalDateTime.now();
    }

}

package me.right.study.common.repository;

import me.right.study.common.domain.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileData, Long> {
}

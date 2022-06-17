package com.elab.elearning.elearning.repository;

import com.elab.elearning.elearning.entity.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileDB, String> {
}

package com.elab.elearning.elearning.repository;

import com.elab.elearning.elearning.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<Module,String> {
}

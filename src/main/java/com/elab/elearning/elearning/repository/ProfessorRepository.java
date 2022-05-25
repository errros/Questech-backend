package com.elab.elearning.elearning.repository;

import com.elab.elearning.elearning.entity.Professor;
import com.elab.elearning.elearning.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {


    @Query
    Optional<Professor> findByEmail(String email);


}

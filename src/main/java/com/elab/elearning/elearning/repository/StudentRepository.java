package com.elab.elearning.elearning.repository;

import com.elab.elearning.elearning.entity.Professor;
import com.elab.elearning.elearning.entity.Student;
import com.elab.elearning.elearning.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {



    @Query
    Optional<User> findByEmail(String email);

}

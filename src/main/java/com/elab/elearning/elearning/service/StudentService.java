package com.elab.elearning.elearning.service;


import com.elab.elearning.elearning.entity.Student;
import com.elab.elearning.elearning.entity.User;
import com.elab.elearning.elearning.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Optional<User> add(Student p ){

        studentRepository.save(p);
        return studentRepository.findByEmail(p.getEmail());
    }

}

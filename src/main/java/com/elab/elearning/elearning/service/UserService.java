package com.elab.elearning.elearning.service;


import com.elab.elearning.elearning.entity.Professor;
import com.elab.elearning.elearning.entity.Student;
import com.elab.elearning.elearning.entity.User;
import com.elab.elearning.elearning.repository.UserRepository;
import com.elab.elearning.elearning.model.UserRole;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MailService mailService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ProfessorService professorService;

    public void delete(Long id) {
        userRepository.deleteById(id);
    }


    //don't forget to add validators when updating a user
/*
    public User updateInfos(Optional<String> username, Optional<String> familName,
                            Optional<String> firstName, Optional<Date> birthDate,
                            Optional<String> birth, Optional<String> password, Optional<String> placeBirth,
                            Long userid) {


        User user = userRepository.findById(userid).get();
        if (username.isPresent() ) {
            user.setUsername(username.get());
        }
        if (familName.isPresent()) {
            user.setFamilyname(familName.get());
        }
        if (firstName.isPresent()) {
            user.setFirstname(firstName.get());
        }
        if (birthDate.isPresent()) {
            user.setBirthDate(birthDate.get());
        }
        if (placeBirth.isPresent()) {
            user.setPlaceBirth(placeBirth.get());
        }
        if ((oldPassword.isPresent()) && (newPassword.isPresent()) && (passwordEncoder.matches(oldPassword.get(), user.getPassword()))) {
            user.setPassword(passwordEncoder.encode(newPassword.get()));
        }

        userRepository.save(user);

        return userRepository.getById(userid);
    }


*/

    public Optional<User> findByUsername(String username){


        return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

}

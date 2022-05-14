package com.elab.elearning.elearning;

import com.elab.elearning.elearning.entity.Professor;
import com.elab.elearning.elearning.entity.User;
import com.elab.elearning.elearning.model.Promo;
import com.elab.elearning.elearning.repository.ModuleRepository;
import com.elab.elearning.elearning.repository.UserRepository;
import com.elab.elearning.elearning.model.UserRole;
import com.elab.elearning.elearning.service.FileService;
import com.elab.elearning.elearning.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.elab.elearning.elearning.entity.Module;

import javax.annotation.Resource;
import java.sql.Date;


@Component
public class Init implements CommandLineRunner {

    private static final String email = "admin@admin.com";


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Resource
    FileService storageService;





    @Override
    public void run(String... args) throws Exception {

       if(userRepository.findByEmail(email).isEmpty()) {
           User u = new User("admin", "admin", Date.valueOf("1995-05-05")
                   , "world", passwordEncoder.encode("admin"), UserRole.ADMIN
                   , email);
           userRepository.save(u);

       }


//        storageService.deleteAll();
        storageService.init();

    }



}

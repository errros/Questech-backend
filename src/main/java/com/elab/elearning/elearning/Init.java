package com.elab.elearning.elearning;

import com.elab.elearning.elearning.entity.Professor;
import com.elab.elearning.elearning.entity.Student;
import com.elab.elearning.elearning.entity.User;
import com.elab.elearning.elearning.model.Promo;
import com.elab.elearning.elearning.model.Sex;
import com.elab.elearning.elearning.repository.ModuleRepository;
import com.elab.elearning.elearning.repository.ProfessorRepository;
import com.elab.elearning.elearning.repository.StudentRepository;
import com.elab.elearning.elearning.repository.UserRepository;
import com.elab.elearning.elearning.model.UserRole;
import com.elab.elearning.elearning.service.CourseService;
import com.elab.elearning.elearning.service.FileService;
import com.elab.elearning.elearning.service.ProfessorService;
import com.elab.elearning.elearning.service.TdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.elab.elearning.elearning.entity.Module;

import javax.annotation.Resource;
import java.sql.Date;


@Component
public class Init implements CommandLineRunner {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ProfessorRepository professorRepository;
    @Resource
    private TdService tdService;
    @Resource
    private CourseService courseService;
    @Resource
    FileService storageService;






    @Override
    public void run(String... args) throws Exception {

        String email = "admin@admin.com";


        if(userRepository.findByEmail(email).isEmpty()) {
           User u = new User("admin", "admin", Date.valueOf("1995-05-05")
                   , "world", passwordEncoder.encode("admin"), UserRole.ADMIN
                   , email);
           userRepository.save(u);

       }

        email = "student@student.com";

        if(studentRepository.findByEmail(email).isEmpty()) {
            Student s = new Student("student", "student", Date.valueOf("1995-05-05")
                    , "world", passwordEncoder.encode("student"),Sex.MALE
                    , email);
            studentRepository.save(s);

        }

     email = "professor@professor.com";



        if(professorRepository.findByEmail(email).isEmpty()) {
            Professor s = new Professor("professor", "professor", Date.valueOf("1995-05-05")
                    , "world", passwordEncoder.encode("professor")
                    , email,"0790801843","prof level");
            professorRepository.save(s);

        }


        storageService.init();
        tdService.init();
        courseService.init();

    }



}

package com.elab.elearning.elearning.apicontroller;

import com.elab.elearning.elearning.entity.Module;
import com.elab.elearning.elearning.entity.Professor;
import com.elab.elearning.elearning.entity.Student;
import com.elab.elearning.elearning.entity.User;
import com.elab.elearning.elearning.message.ResponseMessage;
import com.elab.elearning.elearning.model.ProfessorRegistration;
import com.elab.elearning.elearning.model.Promo;
import com.elab.elearning.elearning.model.StudentRegistration;
import com.elab.elearning.elearning.model.UserRole;
import com.elab.elearning.elearning.service.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/admin")
@PreAuthorize(value = "hasAuthority('ADMIN')")
public class AdminController {


    @Autowired
    private UserService userService;
    @Autowired
    private ProfessorService professorService;
    @Autowired
    private StudentService studentService;

    @Autowired
    private ModuleService moduleService;
    //endpoint to register all the users ,requestbody is the entity user(username,password,email,role)
    @Autowired
    FileService storageService;

    @Operation(summary = "register a professor", description = "the user id is attributed by RDBMS", security = {@SecurityRequirement(name = "bearer-key")})
    @PostMapping("/user/professor")
    public Optional<Professor> register(@Valid @RequestBody ProfessorRegistration user) {

        return professorService.register(user);

    }

    @Operation(summary = "register a student", description = "the user id is attributed by RDBMS", security = {@SecurityRequirement(name = "bearer-key")})
    @PostMapping("/user/student")
    public Student register(@Valid @RequestBody StudentRegistration user) {


        return studentService.register(user).get();

    }





    @GetMapping(value = "user")
    @Operation(summary = "retrieve all users", security = {@SecurityRequirement(name = "bearer-key")})
    List<User> getAllUsers() {

        return userService.getAllUsers();

    }


    @GetMapping(value = "user/{id}")
    @Operation(summary = "retrieve a single user", security = {@SecurityRequirement(name = "bearer-key")})
    Optional<User> getUser(@PathVariable("id") Long id) {

        return userService.getUser(id);

    }


    @GetMapping(value = "user/professor")
    @Operation(summary = "retrieve all professors", security = {@SecurityRequirement(name = "bearer-key")})
    List<Professor> getAllProfessors() {

        return professorService.getAllProfessors();

    }



    @GetMapping(value = "user/student")
    @Operation(summary = "retrieve all students", security = {@SecurityRequirement(name = "bearer-key")})
    List<Student> getAllStudents() {

        return studentService.getAllStudents();

    }


    @GetMapping(value = "user/professor/{id}")
    @Operation(summary = "retrieve a single professor", security = {@SecurityRequirement(name = "bearer-key")})
    Optional<Professor> getProfessor(@PathVariable("id") Long id){

        return professorService.getProfessor(id);

    }



    @GetMapping(value = "user/student/{id}")
    @Operation(summary = "retrieve a single student", security = {@SecurityRequirement(name = "bearer-key")})
    Optional<Student> getStudent(@PathVariable("id") Long id) {

        return studentService.getStudent(id);

    }



    @PutMapping(value = "user/professor/{id}")
    @Operation(summary = "update professor / change his password", security = {@SecurityRequirement(name = "bearer-key")})
    Professor update(@RequestParam Optional<String> username, @RequestParam Optional<String> familName,
                               @RequestParam Optional<String> firstName, @RequestParam Optional<Date> birthDate,
                               @RequestParam Optional<String> placeBirth,@RequestParam Optional<String> phoneNumber,
                               @RequestParam Optional<String> academicLevel,
                               @RequestParam Optional<String> newPassword, @PathVariable("id") Long userid) {

        return professorService.update(username,familName,firstName,birthDate,placeBirth,phoneNumber,academicLevel,newPassword,userid);

    }


    @PutMapping("student/{id}")
    @Operation(summary = "update student profile / change his password", security = {@SecurityRequirement(name = "bearer-key")})
    public Student update(@RequestParam Optional<String> username, @RequestParam Optional<String> familName,
                       @RequestParam Optional<String> firstName, @RequestParam Optional<Date> birthDate,
                       @RequestParam Optional<String> placeBirth,

                       @RequestParam Optional<String> newPassword, @PathVariable("id") Long userid) {

        return studentService.updateInfos(username, familName,
                firstName, birthDate,
                placeBirth,
                newPassword, userid
        );


    }




    @DeleteMapping("user/{id}")
    @Operation(summary = "delete a user", security = {@SecurityRequirement(name = "bearer-key")})

    public void delete(@PathVariable("id") int id) {
        userService.delete((long) id);
    }


    @PostMapping("/user/professor/upload")
    @Operation(summary = "register professors from excel file",
            description = "excel file format : firstname , familyname , email , date of birth , place of birth ",
            security = {@SecurityRequirement(name = "bearer-key")})
    public String UploadExcelFileProf(@RequestParam("file") MultipartFile file) throws IOException {
        professorService.saveExcelSheet(file);
        return "excel parsed and professors registred successfully";
    }

    @PostMapping("/user/student/upload")
    @Operation(summary = "register students from excel file",
            description = "excel file format : firstname , familyname , email , date of birth , place of birth ",
            security = {@SecurityRequirement(name = "bearer-key")})
    public String UploadExcelFileStudent(@RequestParam("file") MultipartFile file) throws IOException {
        studentService.saveExcelSheet(file);
        return "excel parsed and students registred successfully";
    }


    @GetMapping(value = "module")
    @Operation(summary = "retrieve all modules", security = {@SecurityRequirement(name = "bearer-key")})
    List<Module> getAllModules() {

        return moduleService.getAllModules();

    }


    @GetMapping(value = "module/{code}")
    @Operation(summary = "retrieve a single module", security = {@SecurityRequirement(name = "bearer-key")})
    Optional<Module> getModule(@PathVariable("code") String code) {

        return moduleService.getModule(code);

    }

    @Operation(summary = "add a module",  security = {@SecurityRequirement(name = "bearer-key")})
    @PostMapping("module")
    public Module add(@Valid @RequestBody Module module,Optional<Long> professorId) {

        return moduleService.add(module,professorId);

    }




    @DeleteMapping("module/{code}")
    @Operation(summary = "delete a module", security = {@SecurityRequirement(name = "bearer-key")})

    public void delete(@PathVariable("code") String code) {
        moduleService.delete(code);
    }





    @PutMapping("module/{code}")
    @Operation(summary = "update module", security = {@SecurityRequirement(name = "bearer-key")})
    public Module update(@RequestParam Optional<String> detailedName,
                         @RequestParam Optional<Promo> promo , @Valid @RequestParam Optional<Integer> semester,
                         @Valid @RequestParam Optional<Integer> coefficient, @Valid @RequestParam Optional<Integer> credit,
                         @Valid @RequestParam Optional<Long> professorId, @PathVariable("code") String code) {

        return  moduleService.update(code,detailedName,promo,semester,coefficient,credit,professorId);


    }
    @PostMapping("/upload/emploi-du-temps")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.save(file);
            message = "Uploaded successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }


    @GetMapping
    String goAdmin() {

        return "WELCOME Admin!";

    }

}

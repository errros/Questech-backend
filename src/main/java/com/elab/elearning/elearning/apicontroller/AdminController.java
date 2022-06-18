package com.elab.elearning.elearning.apicontroller;

import com.elab.elearning.elearning.entity.*;
import com.elab.elearning.elearning.entity.Module;
import com.elab.elearning.elearning.message.ResponseMessage;
import com.elab.elearning.elearning.model.*;
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
import java.time.DayOfWeek;
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
    private FileService storageService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private SessionService sessionService;
    @Autowired
    private FileStorageService fileStorageService;

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



    @PutMapping("student/{id}")
    @Operation(summary = "update student profile / change his password", security = {@SecurityRequirement(name = "bearer-key")})
    public Student update(@RequestBody Student student) {

        return studentService.updateInfos(student);


    }

    @PutMapping(value = "user/professor/{id}")
    @Operation(summary = "update professor , change password", security = {@SecurityRequirement(name = "bearer-key")})
    Professor update(@RequestBody  Professor professor) {

        return professorService.updateInfo(professor);

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



    @PostMapping("/upload/emploi-du-temps")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.save(file);
            fileStorageService.store(file);
            message = "Uploaded successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @Operation(summary = "add a location",  security = {@SecurityRequirement(name = "bearer-key")})
    @PostMapping("location")
    public Location add(@Valid @RequestBody Location location) {

        return locationService.add(location);

    }


    @GetMapping(value = "location")
    @Operation(summary = "retrieve all locations", security = {@SecurityRequirement(name = "bearer-key")})
    List<Location> getAllLocations() {

        return locationService.getAllLocations();

    }


    @GetMapping(value = "location/{id}")
    @Operation(summary = "retrieve a single location", security = {@SecurityRequirement(name = "bearer-key")})
    Optional<Location> getLocation(@PathVariable("id") Long id) {

        return locationService.getLocation(id);

    }
    @DeleteMapping("location/{id}")
    @Operation(summary = "delete a location", security = {@SecurityRequirement(name = "bearer-key")})
    public void deleteLocation(@PathVariable("id") int id) {
        locationService.delete((long) id);
    }




    @PostMapping("session/{day}/{promo}/{groupNum}")
    @Operation(summary = "add sessions " ,description = "", security = {@SecurityRequirement(name = "bearer-key")})
    public void addDaySessions(@PathVariable("day") DayOfWeek day , @PathVariable("promo") Promo promo,
                               @PathVariable("groupNum") Long id,@RequestBody @Valid  SessionRegistration sessionRegistration){


     sessionService.addSessions(day,promo,id,sessionRegistration);

    }


    @DeleteMapping("session/{day}/{promo}/{groupNum}")
    @Operation(summary = "reset a day "
            ,description = "reset a day is equiavalent to deleting all sessions of a group in that day",
            security = {@SecurityRequirement(name = "bearer-key")})
    public void resetDay(@RequestParam("day") DayOfWeek day , @RequestParam("promo") Promo promo,
                           @RequestParam("groupNum") Long id){


        sessionService.resetDay(day,promo,id);

    }





    @GetMapping
    String goAdmin() {

        return "WELCOME Admin!";

    }

}

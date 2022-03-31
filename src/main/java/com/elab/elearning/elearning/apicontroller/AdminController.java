package com.elab.elearning.elearning.apicontroller;

import com.elab.elearning.elearning.authentication.User;
import com.elab.elearning.elearning.authentication.UserRole;
import com.elab.elearning.elearning.authentication.UserService;
import com.elab.elearning.elearning.model.UserRegistration;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/admin")
@PreAuthorize(value = "hasAuthority('ADMIN')")
@CrossOrigin(origins = "*")
//admin control panel endpoints
public class AdminController {


    @Autowired
    private UserService userService;

  //endpoint to register all the users ,requestbody is the entity user(username,password,email,role)

    @Operation(summary = "register a user", description = "the user id is attributed by RDBMS" ,security = {@SecurityRequirement(name = "bearer-key")})
    @PostMapping("/user/registration")
    public User register(@Valid @RequestBody UserRegistration user, @RequestParam(name = "role")UserRole role){

        return userService.register(user,role);

    }

    @GetMapping(value = "user")
    @Operation(summary = "retrieve all users",security = { @SecurityRequirement(name = "bearer-key") })
    List<User> getAllUsers(){

        return userService.getAllUsers();

    }


    @GetMapping(value = "user/{id}")
    @Operation(summary = "retrieve a single user",security = { @SecurityRequirement(name = "bearer-key") })
    Optional<User> getUser(@PathVariable("id") Long id){

        return userService.getUser(id);

    }

    @PostMapping("/user/professor/upload")
    @Operation(summary = "register professors from excel file",
            description = "excel file format : firstname , familyname , email , date of birth , place of birth " ,
            security = { @SecurityRequirement(name = "bearer-key") })
    public String UploadExcelFileProf(@RequestParam("file") MultipartFile file) throws IOException {
        userService.saveExcelSheet(file,UserRole.PROFESSOR);
        return "excel parsed and professors registred successfully";
    }

    @PostMapping("/user/student/upload")

    @Operation(summary = "register students from excel file",
            description = "excel file format : firstname , familyname , email , date of birth , place of birth " ,
            security = { @SecurityRequirement(name = "bearer-key") })
    public String UploadExcelFileStudent(@RequestParam("file") MultipartFile  file) throws IOException {
        userService.saveExcelSheet(file,UserRole.STUDENT);
        return "excel parsed and students registred successfully";
    }




    @GetMapping
    String goAdmin(){

        return "WELCOME Admin!";

    }

}

package com.elab.elearning.elearning.apicontroller;

import com.elab.elearning.elearning.authentication.User;
import com.elab.elearning.elearning.authentication.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/admin")
@PreAuthorize(value = "hasAuthority('ADMIN')")
//admin control panel endpoints
public class AdminController {


    @Autowired
    private UserService userService;

  //endpoint to register all the users ,requestbody is the entity user(username,password,email,role)
    @PostMapping("/user/registration")
    public String register(@RequestBody User user){

        userService.register(user);
        return user.toString();
    }

    @GetMapping(value = "user/")
    List<User> getAllUsers(){

        return userService.getAllUsers();

    }

    @GetMapping(value = "user/{id}")
    Optional<User> getUser(@PathVariable("id") Long id){

        return userService.getUser(id);

    }



    @GetMapping
    String goAdmin(){

        return "WELCOME Admin!";

    }

}

package com.elab.elearning.elearning.apicontroller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")

public class HomeController {


    @GetMapping
    String goHome(){

        return "WELCOME HOME!";

    }



}

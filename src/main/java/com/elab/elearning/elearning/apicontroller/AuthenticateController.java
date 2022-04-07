package com.elab.elearning.elearning.apicontroller;


import com.elab.elearning.elearning.jwt.JWTUtility;
import com.elab.elearning.elearning.jwt.JwtRequestModel;
import com.elab.elearning.elearning.jwt.JwtResponseModel;
import com.elab.elearning.elearning.authentication.ApplicationUserService;

import com.elab.elearning.elearning.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//contain one endpoint /authenticate used to generate JWT
//pre added users in the resources database ("admin","admin","admin@admin.com","ADMIN") ,
// ("student","student","student@student.com","STUDENT")
// ("professor","professor","professor@professor.com","PROFESSOR")

@RestController
public class AuthenticateController {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ApplicationUserService applicationUserService;

    @Autowired
    private UserService userService;


    
   //requestbody contains(username,password)
    // validate credentials (login) and generate jwt that will be used in all the upcoming HTTP requests for the token valid time
    @Operation(summary = "user authentication",
            description = "a successful authentication will result in a JWTToken that will be used in the header of all http requests that need authorization"

    )
    @PostMapping("/authenticate")
    public JwtResponseModel authenticate(@RequestBody JwtRequestModel jwtRequestModel) throws Exception{


        System.out.println("this is user email " + jwtRequestModel.getEmail());

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequestModel.getEmail(),
                            jwtRequestModel.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

         UserDetails userDetails
                = applicationUserService.loadUserByUsername(jwtRequestModel.getEmail());


        final String token =
                jwtUtility.generateToken(userDetails);

        return  new JwtResponseModel(token);
    }


    @Operation(summary = "jwt validation",
            description = "check if the token is exipred or it's still valid , returns true if the token is still valid else it's a 500 Error "

    )
    @PostMapping("/validate-token")
    public Boolean validate(@RequestBody JwtResponseModel jwt){


          return !jwtUtility.isTokenExpired(jwt.getJwtToken());
    }



}

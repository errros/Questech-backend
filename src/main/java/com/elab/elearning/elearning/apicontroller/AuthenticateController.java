package com.elab.elearning.elearning.apicontroller;


import com.elab.elearning.elearning.jwt.JWTUtility;
import com.elab.elearning.elearning.jwt.JwtRequestModel;
import com.elab.elearning.elearning.jwt.JwtResponseModel;
import com.elab.elearning.elearning.authentication.ApplicationUserService;
import com.elab.elearning.elearning.authentication.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

//contain one endpoint /authenticate used to generate JWT
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
    // validate credentials (login) and generate jwt that will be used in all the upcoming HTTP requests
    @PostMapping("/authenticate")
    public JwtResponseModel authenticate(@RequestBody JwtRequestModel jwtRequestModel) throws Exception{

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequestModel.getUsername(),
                            jwtRequestModel.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails
                = applicationUserService.loadUserByUsername(jwtRequestModel.getUsername());

        final String token =
                jwtUtility.generateToken(userDetails);

        return  new JwtResponseModel(token);
    }

}

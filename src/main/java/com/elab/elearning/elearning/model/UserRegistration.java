package com.elab.elearning.elearning.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistration {
    private String username;
    ///ADMIN,STUDENT,PROFESSOR
    private String Role;
    @Email
    private String email;



}

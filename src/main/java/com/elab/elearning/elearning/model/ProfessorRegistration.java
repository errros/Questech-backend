package com.elab.elearning.elearning.model;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.sql.Date;


@Data
public class ProfessorRegistration {


    @NotNull
    @NotBlank( message = "wrong first name")
    private String firstname;
    @NotNull
    @NotBlank( message = "wrong family name")
    private String familyname;

    @NotNull
    //@Past
    private Date birthDate;
    @NotNull
    @NotBlank
    private String placeBirth;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String academicLevel;







}

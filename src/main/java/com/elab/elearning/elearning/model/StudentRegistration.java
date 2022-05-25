package com.elab.elearning.elearning.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.*;
import java.sql.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRegistration {


    @NotNull
    @NotBlank( message = "wrong first name")
    private String firstname;
    @NotNull
    @NotBlank( message = "wrong family name")
    private String familyname;

    @NotNull
    @Past
    private Date birthDate;
    @NotNull
    @NotBlank
    private String placeBirth;

    @NotNull
    @Email
    private String email;






}

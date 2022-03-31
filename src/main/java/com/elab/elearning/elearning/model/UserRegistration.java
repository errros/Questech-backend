package com.elab.elearning.elearning.model;

import com.elab.elearning.elearning.authentication.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.sql.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistration {


    @NotNull
    @NotBlank( message = "wrong first name")
    private String firstname;
    @NotNull
    @NotBlank( message = "wrong family name")
    private String familyname;

    @NotNull
//    @Past
    private Date birthDate;
    @NotNull
    @NotBlank
    private String placeBirth;

    @NotNull
    @Email
    private String email;








}

package com.elab.elearning.elearning.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequestModel {
    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(min = 8 , message = "password size should be longer than 8 characters")
    private String password;
}

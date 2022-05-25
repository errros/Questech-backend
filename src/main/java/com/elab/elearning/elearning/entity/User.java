package com.elab.elearning.elearning.entity;


import com.elab.elearning.elearning.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotNull
    private String username;
    @Column
    @NotNull
    @NotBlank( message = "wrong first name")
    private String firstname;
    @Column
    @NotNull
    @NotBlank( message = "wrong family name")
    private String familyname;

    @Column
    @NotNull
    //@Past
    private Date birthDate;

    @Column
    @NotNull
    @NotBlank
    private String placeBirth;

    //password should be at least 9 characters long
    @Column
    @NotNull
    @Size(min = 8 , message = "password size should be longer than 8 characters")
    private String password;
    @Column
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    ///ADMIN,STUDENT,PROFESSOR
    private UserRole role;
    @Column(unique = true)
    @NotNull
    @Email
    private String email;


    public User(String firstname, String familyname, Date birthDate, String placeBirth , String password, UserRole role, String email) {
        this.username = firstname+"."+familyname+birthDate.toString();
        this.firstname = firstname;
        this.familyname = familyname;
        this.birthDate = birthDate;
        this.placeBirth = placeBirth;
        this.password = password;
        this.role = role;
        this.email = email;
    }


}

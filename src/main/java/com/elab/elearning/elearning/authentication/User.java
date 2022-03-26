package com.elab.elearning.elearning.authentication;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    //password should be at least 9 characters long
    @Column(unique = true)
    private String password;
    @Column(unique = true)
    ///ADMIN,STUDENT,PROFESSOR
    private String Role;
    @Column(unique = true)
    private String email;

    public User(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        Role = role;
    }


}

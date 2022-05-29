package com.elab.elearning.elearning.entity;


import com.elab.elearning.elearning.model.Sex;
import com.elab.elearning.elearning.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.sql.Date;

@NoArgsConstructor

@Entity
public class Student extends User {


    public Student(String firstname, String familyname, Date birthDate, String placeBirth, String password, Sex sex, String email) {
        super(firstname, familyname, birthDate, placeBirth, password,UserRole.STUDENT, sex,email);
    }
}

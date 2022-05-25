package com.elab.elearning.elearning.entity;


import com.elab.elearning.elearning.model.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Professor extends User implements Serializable{


    @Column
    private String phoneNumber;

    @Column
    private String academicLevel;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "module_id", referencedColumnName = "code",unique = true)
    private Module module;



    public Professor(String firstname, String familyname, Date birthDate, String placeBirth, String password, String email, Module module , String phoneNumber,String academicLevel) {
        super(firstname, familyname, birthDate, placeBirth, password, UserRole.PROFESSOR, email);
        this.module = module;
        this.academicLevel = academicLevel;
        this.phoneNumber = phoneNumber;

    }

    public Professor(String firstname, String familyname, Date birthDate, String placeBirth, String password, String email,String phoneNumber,String academicLevel) {
        super(firstname, familyname, birthDate, placeBirth, password, UserRole.PROFESSOR, email);
        this.module = null;
        this.phoneNumber = phoneNumber;
        this.academicLevel = academicLevel;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAcademicLevel() {
        return academicLevel;
    }

    public void setAcademicLevel(String academicLevel) {
        this.academicLevel = academicLevel;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }
}

package com.elab.elearning.elearning.entity;


import com.elab.elearning.elearning.model.Sex;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Professor extends User implements Serializable{


    @Column
    private String phoneNumber;

    @Column
    private String academicLevel;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,CascadeType.REMOVE
    })
    @JoinTable(name = "teach",
            joinColumns = @JoinColumn(name = "profid"),
            inverseJoinColumns = @JoinColumn(name = "modulecode"))
    private Set<Module> modules = new HashSet<>();





    public Professor(String firstname, String familyname, Date birthDate, String placeBirth, String password, String email, Sex sex , Set<Module> modules , String phoneNumber, String academicLevel) {
        super(firstname, familyname, birthDate, placeBirth, password, UserRole.PROFESSOR, sex ,email);
        this.modules = modules;
        this.academicLevel = academicLevel;
        this.phoneNumber = phoneNumber;

    }

    public Professor(String firstname, String familyname, Date birthDate, String placeBirth, String password, Sex sex , String email,String phoneNumber,String academicLevel) {
        super(firstname, familyname, birthDate, placeBirth, password, UserRole.PROFESSOR,sex, email);
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

    public Set<Module> getModules() {
        return modules;
    }

    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }
}

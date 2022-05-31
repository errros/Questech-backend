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
    @JoinTable(name = "assist",
            joinColumns = @JoinColumn(name = "profid"),
            inverseJoinColumns = @JoinColumn(name = "modulecode"))
    private Set<Module> modulesAssist = new HashSet<>();


    @OneToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,CascadeType.REMOVE
    } , mappedBy = "responsable")
    private Set<Module> modulesResponsable = new HashSet<>();







    public Professor(String firstname, String familyname, Date birthDate, String placeBirth, String password, String email,String phoneNumber,String academicLevel) {
        super(firstname, familyname, birthDate, placeBirth, password, UserRole.PROFESSOR, email);
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

    public Set<Module> getModulesAssist() {
        return modulesAssist;
    }

    public void setModulesAssist(Set<Module> modulesAssist) {
        this.modulesAssist = modulesAssist;
    }

    public Set<Module> getModulesResponsable() {
        return modulesResponsable;
    }

    public void setModulesResponsable(Set<Module> modulesResponsable) {
        this.modulesResponsable = modulesResponsable;
    }
}

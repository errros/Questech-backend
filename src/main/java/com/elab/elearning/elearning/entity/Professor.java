package com.elab.elearning.elearning.entity;


import com.elab.elearning.elearning.model.Sex;
import com.elab.elearning.elearning.model.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Getter
@Setter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Professor extends User implements Serializable{


    @Column
    private String phoneNumber;

    @Column
    private String academicLevel;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "assist",
            joinColumns = @JoinColumn(name = "profid"),
            inverseJoinColumns = @JoinColumn(name = "modulecode"))
    private Set<Module> modulesAssist = new HashSet<>();


    @OneToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    } , mappedBy = "responsable")
    private Set<Module> modulesResponsable = new HashSet<>();


    @JsonIgnore
    @OneToMany(mappedBy = "professor")
    private Set<Session> sessions = new HashSet<>();






    public Professor(String firstname, String familyname, Date birthDate, String placeBirth, String password, String email,String phoneNumber,String academicLevel) {
        super(firstname, familyname, birthDate, placeBirth, password, UserRole.PROFESSOR, email);
        this.phoneNumber = phoneNumber;
        this.academicLevel = academicLevel;
    }


}

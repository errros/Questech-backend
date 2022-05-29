package com.elab.elearning.elearning.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
public class ProfessorRegistration {


    @NotNull
    @NotBlank( message = "wrong first name")
    private String firstname;
    @NotNull
    @NotBlank( message = "wrong family name")
    private String familyname;

    @NotNull
    //@Past
    private Date birthDate;
    @NotNull
    @NotBlank
    private String placeBirth;

    @NotNull
    @Email
    private String email;

    @Column
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    ///Male , FEMALE
    private Sex sex;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String academicLevel;


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPlaceBirth() {
        return placeBirth;
    }

    public void setPlaceBirth(String placeBirth) {
        this.placeBirth = placeBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
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

    public void setSex(String sex) {
        if(sex.equals(Sex.MALE.name())){

            this.sex = Sex.MALE;
        }else if(sex.equals(Sex.FEMALE.name())){
            this.sex = Sex.FEMALE;

        }

    }

}

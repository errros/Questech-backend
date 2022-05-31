package com.elab.elearning.elearning.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.sql.Date;


@NoArgsConstructor
@AllArgsConstructor
public class StudentRegistration {


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
    @Column
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private Sex sex;
    @NotNull
    @Email
    private String email;

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

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }


    public void setSex(String sex) {
        if(sex.equals(Sex.MALE.name())){

            this.sex = Sex.MALE;
        }else if(sex.equals(Sex.FEMALE.name())){
            this.sex = Sex.FEMALE;

        }

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

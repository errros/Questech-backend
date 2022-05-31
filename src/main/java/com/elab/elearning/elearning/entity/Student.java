package com.elab.elearning.elearning.entity;


import com.elab.elearning.elearning.model.Sex;
import com.elab.elearning.elearning.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@NoArgsConstructor
@Data
@Entity
public class Student extends User {

    @Column
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    ///FEMALE,MALE
    private Sex sex;

    @ManyToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumns(value = {
            @PrimaryKeyJoinColumn(name = "promo_id", referencedColumnName = "promo"),
            @PrimaryKeyJoinColumn(name = "group_id", referencedColumnName = "id")
    })
    private Group group;


    public Student(String firstname, String familyname, Date birthDate, String placeBirth, String password, Sex sex, String email) {
        super(firstname, familyname, birthDate, placeBirth, password, UserRole.STUDENT, email);
        this.sex = sex;
    }

    public void setSex(String sex) {
        if (sex.equals(Sex.MALE.name())) {

            this.sex = Sex.MALE;
        } else if (sex.equals(Sex.FEMALE.name())) {
            this.sex = Sex.FEMALE;

        }

    }
        @Override
        public boolean equals (Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            Student student = (Student) o;

            return (this.getId() == student.getId());
        }


    }



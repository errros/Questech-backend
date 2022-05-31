package com.elab.elearning.elearning.entity;


import com.elab.elearning.elearning.model.Sex;
import com.elab.elearning.elearning.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@NoArgsConstructor
@Data
@Entity
public class Student extends User {

    @ManyToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumns(value = {
            @PrimaryKeyJoinColumn(name = "promo_id", referencedColumnName = "promo"),
            @PrimaryKeyJoinColumn(name = "group_id", referencedColumnName = "id")
    })
    private Group group;


    public Student(String firstname, String familyname, Date birthDate, String placeBirth, String password, Sex sex, String email) {
        super(firstname, familyname, birthDate, placeBirth, password,UserRole.STUDENT, sex,email);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Student student = (Student) o;

        return  (this.getId() == student.getId());
    }


}

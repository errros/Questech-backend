package com.elab.elearning.elearning.entity;


import com.elab.elearning.elearning.model.Promo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Module{

    @Column(length = 6)
    @Id
    private String code;
    @Column
    @NotNull
    @NotBlank(message = "invalid module name")
    private String detailedName;
    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private Promo promo;
    @Column
    @NotNull
    @Min(value = 1) @Max(value = 2)
    private int semester;
    @Column
    @NotNull
    @Min(value = 1) @Max(value = 5)
    private int coefficient;
    @Column
    @NotNull
    @Min(value = 1)
    private int credit;

    @OneToOne(mappedBy = "module" , cascade = CascadeType.ALL)
    @JsonIgnore
    private Professor professor;

    public Module() {
    }

    public Module(String code, String detailedName, Promo promo, int semester, int coefficient, int credit, Professor professor) {
        this.code = code;
        this.detailedName = detailedName;
        this.promo = promo;
        this.semester = semester;
        this.coefficient = coefficient;
        this.credit = credit;
        this.professor = professor;
    }



    public Module(String code, String detailedName, Promo promo, int semester, int coefficient, int credit) {
        this.code = code;
        this.detailedName = detailedName;
        this.promo = promo;
        this.semester = semester;
        this.coefficient = coefficient;
        this.credit = credit;
        this.professor = null;
    }



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetailedName() {
        return detailedName;
    }

    public void setDetailedName(String detailedName) {
        this.detailedName = detailedName;
    }

    public Promo getPromo() {
        return promo;
    }

    public void setPromo(Promo promo) {
        this.promo = promo;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }


}

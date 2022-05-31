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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
            },
            mappedBy = "modulesAssist")
    @JsonIgnore
    private Set<Professor> assistants = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
            })
    @JoinColumn(name = "prof_id")
    @JsonIgnore
    private Professor responsable;


    public Module() {
    }




    public Module(String code, String detailedName, Promo promo, int semester, int coefficient, int credit) {
        this.code = code;
        this.detailedName = detailedName;
        this.promo = promo;
        this.semester = semester;
        this.coefficient = coefficient;
        this.credit = credit;
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


    public Set<Professor> getAssistants() {
        return assistants;
    }

    public void setAssistants(Set<Professor> assistants) {
        this.assistants = assistants;
    }

    public Professor getResponsable() {
        return responsable;
    }

    public void setResponsable(Professor responsable) {
        this.responsable = responsable;
    }

    @Override
    public String toString() {
        return "Module{" +
                "code='" + code + '\'' +
                ", detailedName='" + detailedName + '\'' +
                ", promo=" + promo +
                ", semester=" + semester +
                ", coefficient=" + coefficient +
                ", credit=" + credit +
                ", assistants=" + assistants +
                ", responsable=" + responsable +
                '}';
    }
}

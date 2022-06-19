package com.elab.elearning.elearning.entity;


import com.elab.elearning.elearning.model.Promo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

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



@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = {"code"})
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

    @JsonIgnore
    @OneToMany(mappedBy = "module")
    private Set<Session> sessions = new HashSet<>();


    @JsonIgnore
    @OneToMany(mappedBy = "module")
    private Set<FileDB> documents = new HashSet<>();


    @Override
    public String toString() {
        return "Module{" +
                "code='" + code + '\'' +
                ", detailedName='" + detailedName + '\'' +
                ", promo=" + promo +
                ", semester=" + semester +
                ", coefficient=" + coefficient +
                ", credit=" + credit +
                '}';
    }
}

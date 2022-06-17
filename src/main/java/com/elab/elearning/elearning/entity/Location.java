package com.elab.elearning.elearning.entity;

import com.elab.elearning.elearning.model.TypeLocation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    @NotNull
    private String name;
    @Column
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private TypeLocation type;
    @JsonIgnore
    @OneToMany(mappedBy = "location")
    private Set<Session> sessions = new HashSet<>();



}
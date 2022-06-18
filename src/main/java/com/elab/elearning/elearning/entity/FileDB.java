package com.elab.elearning.elearning.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@NoArgsConstructor
@Data
@EqualsAndHashCode(of = {"id"})
@Entity
public class FileDB {
    @Column(length = 100)
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Column
    private String name;
    @Column
    private String type;

    public FileDB(String name, String type) {
        this.name = name;
        this.type = type;

    }

}

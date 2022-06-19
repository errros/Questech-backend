package com.elab.elearning.elearning.entity;

import com.elab.elearning.elearning.model.DocumentType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


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
    private String title;
    @Column
    private DocumentType documentType;
    @ManyToOne
    @JoinColumn(name = "module_reference", referencedColumnName = "code",unique = false)
    private Module module;







    public FileDB(String name ,String title, DocumentType type,Module module) {
        this.name = name;
        this.title = title;
        this.documentType = type;
        this.module = module;

    }


}

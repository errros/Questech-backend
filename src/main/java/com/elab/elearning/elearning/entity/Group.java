package com.elab.elearning.elearning.entity;


import com.elab.elearning.elearning.model.GroupId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "students")
@Entity
@Table(name = "groupe")
public class Group implements Serializable {


    @EmbeddedId
    private GroupId groupId;



    @OneToMany(mappedBy = "group" ,cascade = {CascadeType.MERGE , CascadeType.PERSIST}

            )
    @JsonIgnore
    private List<Student> students = new ArrayList<>();


    public Group(GroupId groupId) {
        this.groupId = groupId;

    }

}

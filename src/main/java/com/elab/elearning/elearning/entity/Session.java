package com.elab.elearning.elearning.entity;


import com.elab.elearning.elearning.model.SessionId;
import com.elab.elearning.elearning.model.SessionType;
import lombok.*;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = {"sessionId"})
@Entity
public class Session {
    @EmbeddedId
    private SessionId sessionId;

    @Column(nullable = false)
    private SessionType type;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;


    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;


}

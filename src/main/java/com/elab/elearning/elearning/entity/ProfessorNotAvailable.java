package com.elab.elearning.elearning.entity;


import com.elab.elearning.elearning.model.ProfessorAvailablityId;
import com.elab.elearning.elearning.model.TimeOfDay;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.DayOfWeek;


@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Entity
@IdClass(ProfessorAvailablityId.class)
public class ProfessorNotAvailable{

    @Id
    private DayOfWeek day;
    @Id
    private TimeOfDay time;



    @Id
    @ManyToOne
    @JoinColumn(name = "professor_id", referencedColumnName = "id",unique = false)
    private Professor professor;




}

package com.elab.elearning.elearning.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.DayOfWeek;


@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
public class ProfessorAvailablityId implements Serializable {

    public DayOfWeek day;
    public TimeOfDay time;
    public Long professor;

}

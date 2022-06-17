package com.elab.elearning.elearning.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;


@Data
public class SessionRegistration {

    @NotNull
    private TimeOfDay time;
    @NotNull
    private SessionType type;
    @NotNull
    private Long professorId;
    @NotNull
    private String moduleCode;

    @NotNull
    private Long location;

}

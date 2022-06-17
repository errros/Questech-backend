package com.elab.elearning.elearning.apicontroller;


import com.elab.elearning.elearning.entity.Location;
import com.elab.elearning.elearning.entity.Professor;
import com.elab.elearning.elearning.entity.Session;
import com.elab.elearning.elearning.model.Promo;
import com.elab.elearning.elearning.model.TimeOfDay;
import com.elab.elearning.elearning.service.SessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
public class PlanningController {

    @Autowired
    private SessionService sessionService;




    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @GetMapping(value = "/resources/location/{day}/{time}")
    @Operation(summary = "retrieve all available locations in a given day , time ", security = {@SecurityRequirement(name = "bearer-key")})
    public Set<Location> getAvailableLocations(@PathVariable(value = "day")DayOfWeek day,
                                                      @PathVariable(value = "time")TimeOfDay time){

           Set<Location> locations = sessionService.getAvailableLocations(day,time);

      return locations;

    }


    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @GetMapping(value = "/resources/professors/{day}/{time}")
    @Operation(summary = "retrieve all available professors in a given day , time ", security = {@SecurityRequirement(name = "bearer-key")})
    public Set<Professor> getAvailableProfessors(@PathVariable(value = "day")DayOfWeek day,
                                               @PathVariable(value = "time")TimeOfDay time){

        Set<Professor> professors = sessionService.getAvailableProfessors(day,time);


        return professors;

    }


    @PreAuthorize(value = "hasAuthority('ADMIN') or hasAuthority('PROFESSOR')")
    @GetMapping(value = "/planning/{professorId}/{day}")
    @Operation(summary = "retrieve all sessions of a professor", security = {@SecurityRequirement(name = "bearer-key")})
    public  Set<Session> getProfessorPlanning(@PathVariable("professorId") Long id ,@PathVariable(value = "day") DayOfWeek day){


        Set<Session> sessions= sessionService.getProfessorPlanning(id,day);


          return sessions;

    }



    @PreAuthorize(value = "hasAuthority('ADMIN') or hasAuthority('STUDENT')")
    @GetMapping(value = "/planning/{promo}/{groupNum}/{day}")
    @Operation(summary = "retrieve all sessions of a group", security = {@SecurityRequirement(name = "bearer-key")})
    public  Set<Session> getGroupPlanning(@PathVariable("promo") Promo promo ,@PathVariable("groupNum") Long id,
                                          @PathVariable DayOfWeek day){

        Set<Session> sessions =  sessionService.getGroupPlanning(promo,id,day);
     return sessions;
    }


}

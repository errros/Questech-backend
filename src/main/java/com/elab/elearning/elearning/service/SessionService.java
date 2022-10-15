package com.elab.elearning.elearning.service;


import com.elab.elearning.elearning.entity.*;
import com.elab.elearning.elearning.entity.Module;
import com.elab.elearning.elearning.model.*;
import com.elab.elearning.elearning.repository.*;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private ProfessorNotAvailableRepository professorNotAvailableRepository;
    @Autowired
    private LocationNotAvailableRepository locationNotAvailableRepository;
    @Autowired
    private ModuleRepository moduleRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private LocationRepository locationRepository;

    public void addSessions(DayOfWeek day, Promo promo, Long id, SessionRegistration sessionRegistration) {



            //check if there's a session collision aka session at same day , time for a given grouo
            GroupId groupId = new GroupId(promo, id);
            SessionId sessionId = new SessionId(day, sessionRegistration.getTime(), groupId);
            Optional<Session> sessionCollision = sessionRepository.findById(sessionId);
            if (sessionCollision.isEmpty()) {
                //check if the professor is free at that given day , time (collison)
                ProfessorAvailablityId paid = new ProfessorAvailablityId(day, sessionRegistration.getTime(), sessionRegistration.getProfessorId());
                Optional<ProfessorNotAvailable> professorCollison = professorNotAvailableRepository.findById(paid);
                if (professorCollison.isEmpty()) {
                    //check if the location is free at that given day , time (collison)
                    LocationAvailablityId laid = new LocationAvailablityId(day, sessionRegistration.getTime(), sessionRegistration.getLocation());
                    Optional<LocationNotAvailable> locationCollison = locationNotAvailableRepository.findById(laid);
                    if (locationCollison.isEmpty()) {
                        //all conditions met , now add the session
                        //the case when there's no module with such a code reference is not treated

                        Module module = moduleRepository.getById(sessionRegistration.getModuleCode());

                        Professor professor = professorRepository.getById(sessionRegistration.getProfessorId());
                        Location location = locationRepository.getById(sessionRegistration.getLocation());
                        Session session = new Session(sessionId, sessionRegistration.getType(), module, professor, location);

                        //update module

                        if(!professor.getModulesAssist().contains(module)) {
                            professor.getModulesAssist().add(module);
                            module.getAssistants().add(professor);


                        }

                        module.getSessions().add(session);
                        professor.getSessions().add(session);
                        location.getSessions().add(session);
                     if(sessionRegistration.getType() == SessionType.COURS) {
                         ProfessorNotAvailable pnaId =new ProfessorNotAvailable(day, sessionRegistration.getTime(), professor);
                         professorNotAvailableRepository.save(pnaId);
                         locationNotAvailableRepository.save(new LocationNotAvailable(day, sessionRegistration.getTime(), location));

                     }
                        sessionRepository.save(session);
                        professorRepository.save(professor);
                        moduleRepository.save(module);
                        locationRepository.save(location);


                    }


                }


            } else {
                throw new OpenApiResourceNotFoundException("a session collison");
            }





    }


    public void resetDay(DayOfWeek day, Promo promo, Long id) {

        Set<Session> sessions = sessionRepository.findByDayAndPromoAndId(day,promo,id);

        sessions.forEach(session -> {




            session.getModule().getSessions().remove(session);
            session.getLocation().getSessions().remove(session);
            session.getProfessor().getSessions().remove(session);


            locationNotAvailableRepository.deleteByDayAndTime(day,session.getSessionId().getTime());

            professorNotAvailableRepository.deleteByDayAndTime(day,session.getSessionId().getTime());
            sessionRepository.delete(session);
                   moduleRepository.save(session.getModule());
                   locationRepository.save(session.getLocation());
                   professorRepository.save(session.getProfessor());

                }
        );


    }


    public Set<Location> getAvailableLocations(DayOfWeek day, TimeOfDay time) {

        //     locationNotAvailableRepository.findAvailable(day,time);

              List<Location> locations = locationRepository.findAll();
              List<LocationNotAvailable> locationsNotAvailable = locationNotAvailableRepository.findByDayAndTime(day,time);

             return locations.stream().filter(location ->
                     !locationsNotAvailable.contains(new LocationNotAvailable(day,time,location))).collect(Collectors.toSet());
    }


    public Set<Professor> getAvailableProfessors(DayOfWeek day, TimeOfDay time) {


        List<Professor> professors = professorRepository.findAll();
        List<ProfessorNotAvailable> professorNotAvailables = professorNotAvailableRepository.findByDayAndTime(day,time);

        return professors.stream().filter(professor ->
                !professorNotAvailables.contains(new ProfessorNotAvailable(day,time,professor))).collect(Collectors.toSet());


    }

    public Set<Session> getProfessorPlanning(Long id, DayOfWeek day) {



     return sessionRepository.findByProfessorAndDay(id,day);



    }

    public Set<Session> getGroupPlanning(Promo promo, Long id, DayOfWeek day) {

       return sessionRepository.findByGroupAndDay(promo,id,day);

    }


}

package com.elab.elearning.elearning.repository;


import com.elab.elearning.elearning.entity.Location;
import com.elab.elearning.elearning.entity.LocationNotAvailable;
import com.elab.elearning.elearning.entity.Professor;
import com.elab.elearning.elearning.entity.ProfessorNotAvailable;
import com.elab.elearning.elearning.model.ProfessorAvailablityId;
import com.elab.elearning.elearning.model.TimeOfDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Repository
public interface ProfessorNotAvailableRepository extends JpaRepository<ProfessorNotAvailable, ProfessorAvailablityId> {


    @Transactional
    @Modifying
    @Query
    void deleteByDayAndTime(DayOfWeek day , TimeOfDay time);

    @Query
    List<ProfessorNotAvailable> findByDayAndTime(DayOfWeek day , TimeOfDay time);


    @Query(value = "SELECT p from Professor p ,ProfessorNotAvailable pn WHERE pn.professor.id <> p.id AND pn.day = ?1 AND pn.time = ?2")
    Set<Professor> findAvailable(DayOfWeek day , TimeOfDay time);


}

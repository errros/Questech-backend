package com.elab.elearning.elearning.repository;

import com.elab.elearning.elearning.entity.Location;
import com.elab.elearning.elearning.entity.LocationNotAvailable;

import com.elab.elearning.elearning.model.LocationAvailablityId;
import com.elab.elearning.elearning.model.TimeOfDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface LocationNotAvailableRepository extends JpaRepository<LocationNotAvailable, LocationAvailablityId> {

     @Transactional
     @Modifying
     @Query
     void deleteByDayAndTime(DayOfWeek day , TimeOfDay time);

     @Query
     List<LocationNotAvailable> findByDayAndTime(DayOfWeek day , TimeOfDay time);


     @Query(value = "SELECT l from Location l , LocationNotAvailable ln WHERE ln.location.id <> l.id AND ln.day = ?1 AND ln.time = ?2 ")
     Set<Location> findAvailable(DayOfWeek day , TimeOfDay time);





}


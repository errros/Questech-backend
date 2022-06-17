package com.elab.elearning.elearning.repository;

import com.elab.elearning.elearning.entity.Location;
import com.elab.elearning.elearning.model.TimeOfDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {
    @Query(value = "SELECT l from Location l , LocationNotAvailable ln WHERE ln.location.id <> l.id ")
    List<Location> findAvailable(DayOfWeek day , TimeOfDay time);

}

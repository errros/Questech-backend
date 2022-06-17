package com.elab.elearning.elearning.entity;


import com.elab.elearning.elearning.model.LocationAvailablityId;
import com.elab.elearning.elearning.model.TimeOfDay;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.DayOfWeek;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
@Entity
@IdClass(LocationAvailablityId.class)
public class LocationNotAvailable {

    @Id
    private DayOfWeek day;
    @Id
    private TimeOfDay time;

    @Id
    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id",unique = false)
    private Location location;


}

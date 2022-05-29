package com.elab.elearning.elearning.service;

import com.elab.elearning.elearning.entity.Location;
import com.elab.elearning.elearning.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public Location add(Location l) {
        locationRepository.save(l);
        return l;
    }
    public void delete(Long id) {
        locationRepository.deleteById(id);
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Optional<Location> getLocation(Long id) {
        return locationRepository.findById(id);
    }
}

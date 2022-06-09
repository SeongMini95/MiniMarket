package com.jsm.mm.service;

import com.jsm.mm.domain.location.Location;
import com.jsm.mm.domain.location.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LocationService {

    private final LocationRepository locationRepository;

    @Transactional(readOnly = true)
    public List<Location> findByInDistance(double x, double y, int distance) {
        return locationRepository.findByInDistance(x, y, distance);
    }
}

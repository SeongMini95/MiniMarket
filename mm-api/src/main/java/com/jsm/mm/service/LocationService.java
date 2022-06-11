package com.jsm.mm.service;

import com.jsm.mm.domain.location.Location;
import com.jsm.mm.domain.location.repository.LocationRepository;
import com.vividsolutions.jts.geom.Point;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LocationService {

    private final LocationRepository locationRepository;

    @Transactional(readOnly = true)
    public List<Location> findByInDistance(Point point, int meter) {
        return locationRepository.findByInDistance(point.toText(), meter);
    }
}

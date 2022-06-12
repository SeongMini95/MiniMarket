package com.jsm.mm.service;

import com.jsm.mm.domain.location.Location;
import com.jsm.mm.domain.location.repository.LocationRepository;
import com.jsm.mm.domain.memberlocation.enums.LocationRange;
import com.jsm.mm.dto.request.location.AroundLocationRequestDto;
import com.jsm.mm.dto.response.location.AroundLocationResponseDto;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LocationService {

    private final LocationRepository locationRepository;

    @Transactional(readOnly = true)
    public List<Location> findByInDistance(Point point, int meter) {
        return locationRepository.findByInDistance(point.toText(), meter);
    }

    @Transactional(readOnly = true)
    public List<AroundLocationResponseDto> findAroundLocation(AroundLocationRequestDto aroundLocationRequestDto) {
        GeometryFactory factory = new GeometryFactory();

        Point point = factory.createPoint(new Coordinate(aroundLocationRequestDto.getX(), aroundLocationRequestDto.getY()));
        Location location = locationRepository.findContainLocation(point.toText()).orElseThrow();
        List<Location> locationList = locationRepository.findByInDistance(location.getCentCoords().toText(), LocationRange.LARGE.getMeter());

        return locationList.stream()
                .map(l -> new AroundLocationResponseDto(l.getId(), l.getSggNm() + " " + l.getEmdNm()))
                .collect(Collectors.toList());
    }
}

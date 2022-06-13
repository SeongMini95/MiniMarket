package com.jsm.mm.controller;

import com.jsm.mm.dto.request.location.AroundLocationRequestDto;
import com.jsm.mm.dto.response.location.AroundLocationResponseDto;
import com.jsm.mm.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/location")
public class LocationApiController {

    private final LocationService locationService;

    @PostMapping("/around")
    public List<AroundLocationResponseDto> findAroundLocation(@RequestBody AroundLocationRequestDto aroundLocationRequestDto) {
        return locationService.findAroundLocation(aroundLocationRequestDto);
    }
}

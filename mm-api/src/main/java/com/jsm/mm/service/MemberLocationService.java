package com.jsm.mm.service;

import com.jsm.mm.domain.location.Location;
import com.jsm.mm.domain.location.repository.LocationRepository;
import com.jsm.mm.domain.member.Member;
import com.jsm.mm.domain.memberlocation.MemberLocation;
import com.jsm.mm.domain.memberlocation.repository.MemberLocationRepository;
import com.jsm.mm.domain.memberrangelocation.MemberRangeLocation;
import com.jsm.mm.domain.memberrangelocation.MemberRangeLocationId;
import com.jsm.mm.domain.memberrangelocation.repository.MemberRangeLocationRepository;
import com.jsm.mm.dto.request.memberlocation.MemberLocationSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberLocationService {

    private final MemberLocationRepository memberLocationRepository;
    private final MemberRangeLocationRepository memberRangeLocationRepository;
    private final LocationRepository locationRepository;
    private final LocationService locationService;

    @Transactional
    public void save(MemberLocationSaveRequestDto memberLocationSaveRequestDto, Member member) {
        Location location = locationRepository.findById(memberLocationSaveRequestDto.getLocation()).orElseThrow();
        MemberLocation memberLocation = memberLocationRepository.save(memberLocationSaveRequestDto.toEntity(member, location));

        List<Location> inRangeLocList = locationService.findByInDistance(location.getCentCoords(), memberLocation.getLocationRange().getMeter());
        memberRangeLocationRepository.deleteAllByMember(member);
        memberRangeLocationRepository.saveAllBulk(inRangeLocList.stream()
                .map(loc -> MemberRangeLocation.builder()
                        .id(MemberRangeLocationId.builder()
                                .member(member)
                                .location(loc)
                                .build())
                        .build())
                .collect(Collectors.toList()));
    }
}

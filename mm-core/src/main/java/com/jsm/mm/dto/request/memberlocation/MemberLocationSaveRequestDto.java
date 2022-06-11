package com.jsm.mm.dto.request.memberlocation;

import com.jsm.mm.domain.location.Location;
import com.jsm.mm.domain.member.Member;
import com.jsm.mm.domain.memberlocation.MemberLocation;
import com.jsm.mm.domain.memberlocation.MemberLocationId;
import com.jsm.mm.domain.memberlocation.enums.LocationRange;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberLocationSaveRequestDto {

    private String location;
    private int seq;
    private String range;

    @Builder
    public MemberLocationSaveRequestDto(String location, int seq, String range) {
        this.location = location;
        this.seq = seq;
        this.range = range;
    }

    public MemberLocation toEntity(Member member, Location location) {
        return MemberLocation.builder()
                .id(MemberLocationId.builder()
                        .member(member)
                        .location(location)
                        .locationSeq(seq)
                        .build())
                .locationRange(LocationRange.ofCode(range))
                .build();
    }
}

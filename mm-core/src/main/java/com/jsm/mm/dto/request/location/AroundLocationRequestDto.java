package com.jsm.mm.dto.request.location;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AroundLocationRequestDto {

    private double x;
    private double y;

    @Builder
    public AroundLocationRequestDto(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

package com.jsm.mm.domain.memberlocation.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum LocationRange {

    ONLY("본인 지역만", "1", 0),
    SMALL("소", "2", 10),
    MEDIUM("중", "3", 12),
    LARGE("대", "4", 15);

    private final String desc;
    private final String code;
    private final int distance;

    public static LocationRange ofCode(String code) {
        return Arrays.stream(LocationRange.values())
                .filter(c -> c.getCode().equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("LocationRange, code=[%s]가 존재하지 않습니다.", code)));
    }
}

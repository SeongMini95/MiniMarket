package com.jsm.mm.domain.member.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RoleName {

    GUEST("손님", "ROLE_GUEST"),
    USER("일반 사용자", "ROLE_USER");

    private final String desc;
    private final String role;
}

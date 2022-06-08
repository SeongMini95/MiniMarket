package com.jsm.mm.domain.certify.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum CertifyCd {

    SIGN_UP("회원가입", "1"),
    FIND_PWD("비밀번호 찾기", "2");

    private final String desc;
    private final String code;

    public static CertifyCd ofCode(String code) {
        return Arrays.stream(CertifyCd.values())
                .filter(c -> c.getCode().equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("CertifyCd, code=[%s]가 존재하지 않습니다.", code)));
    }
}

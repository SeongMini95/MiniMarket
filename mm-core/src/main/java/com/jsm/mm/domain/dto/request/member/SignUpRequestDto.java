package com.jsm.mm.domain.dto.request.member;

import com.jsm.mm.domain.member.Member;
import com.jsm.mm.domain.member.enums.RoleName;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SignUpRequestDto {

    private String username;
    private String password;
    private String email;
    private String nickname;

    @Builder
    public SignUpRequestDto(String username, String password, String email, String nickname) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
    }

    public Member toMemberEntity(String encPassword) {
        return Member.builder()
                .username(username)
                .password(encPassword)
                .email(email)
                .nickname(nickname)
                .roleName(RoleName.GUEST)
                .isLeave(false)
                .build();
    }
}

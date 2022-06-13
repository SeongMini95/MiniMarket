package com.jsm.mm.service;

import com.jsm.mm.domain.certify.Certify;
import com.jsm.mm.domain.certify.enums.CertifyCd;
import com.jsm.mm.domain.member.Member;
import com.jsm.mm.domain.member.repository.MemberRepository;
import com.jsm.mm.dto.request.member.SignUpRequestDto;
import com.jsm.mm.dto.request.memberlocation.MemberLocationSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final CertifyService certifyService;
    private final MemberLocationService memberLocationService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(SignUpRequestDto signUpRequestDto) {
        boolean isExistUsername = memberRepository.isExistMember("username", signUpRequestDto.getUsername());
        if (!isExistUsername) {
            throw new RuntimeException("이미 존재하는 아이디 입니다.");
        }

        boolean isExistEmail = memberRepository.isExistMember("email", signUpRequestDto.getEmail());
        if (!isExistEmail) {
            throw new RuntimeException("이미 존재하는 이메일 입니다.");
        }

        String encPassword = passwordEncoder.encode(signUpRequestDto.getPassword());
        Member member = memberRepository.save(signUpRequestDto.toEntity(encPassword));

        Certify certify = certifyService.save(member, CertifyCd.SIGN_UP, 30L);

        MemberLocationSaveRequestDto memberLocationSaveRequestDto = MemberLocationSaveRequestDto.builder()
                .location(signUpRequestDto.getLocation())
                .seq(1)
                .range(signUpRequestDto.getRange())
                .build();
        memberLocationService.save(memberLocationSaveRequestDto, member);
    }

    @Transactional(readOnly = true)
    public boolean isExistMember(String column, String value) {
        return memberRepository.isExistMember(column, value);
    }
}

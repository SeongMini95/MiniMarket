package com.jsm.mm.service;

import com.jsm.mm.domain.certify.Certify;
import com.jsm.mm.domain.certify.enums.CertifyCd;
import com.jsm.mm.domain.member.Member;
import com.jsm.mm.domain.member.repository.MemberRepository;
import com.jsm.mm.dto.request.member.SignUpRequestDto;
import com.jsm.mm.dto.request.memberlocation.MemberLocationSaveRequestDto;
import com.jsm.mm.eventpublisher.event.SignUpEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
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
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public void signUp(SignUpRequestDto signUpRequestDto) {
        boolean isExistUsername = memberRepository.isExistMember("username", signUpRequestDto.getUsername());
        boolean isExistEmail = memberRepository.isExistMember("email", signUpRequestDto.getEmail());
        if (!isExistUsername || !isExistEmail) {
            throw new RuntimeException();
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

        eventPublisher.publishEvent(new SignUpEvent(member, certify));
    }

    @Transactional(readOnly = true)
    public boolean isExistMember(String column, String value) {
        return memberRepository.isExistMember(column, value);
    }
}

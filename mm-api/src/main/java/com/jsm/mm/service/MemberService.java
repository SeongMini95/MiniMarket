package com.jsm.mm.service;

import com.jsm.mm.domain.certify.Certify;
import com.jsm.mm.domain.certify.enums.CertifyCd;
import com.jsm.mm.domain.dto.request.member.SignUpRequestDto;
import com.jsm.mm.domain.member.Member;
import com.jsm.mm.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final CertifyService certifyService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(SignUpRequestDto signUpRequestDto) {
        String encPassword = passwordEncoder.encode(signUpRequestDto.getPassword());
        Member member = memberRepository.save(signUpRequestDto.toMemberEntity(encPassword));

        Certify certify = certifyService.save(member, CertifyCd.SIGN_UP, 30L);
    }
}

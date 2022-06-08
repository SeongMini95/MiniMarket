package com.jsm.mm.service;

import com.jsm.mm.domain.dto.request.member.SignUpRequestDto;
import com.jsm.mm.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(SignUpRequestDto signUpRequestDto) {
        String encPassword = passwordEncoder.encode(signUpRequestDto.getPassword());
        memberRepository.save(signUpRequestDto.toMemberEntity(encPassword));
    }
}

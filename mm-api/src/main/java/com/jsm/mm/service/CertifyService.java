package com.jsm.mm.service;

import com.jsm.mm.domain.certify.Certify;
import com.jsm.mm.domain.certify.enums.CertifyCd;
import com.jsm.mm.domain.certify.repository.CertifyRepository;
import com.jsm.mm.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class CertifyService {

    private final CertifyRepository certifyRepository;

    @Transactional
    public Certify save(Member member, CertifyCd certifyCd, Long minutes) {
        return certifyRepository.save(Certify.builder()
                .member(member)
                .certifyKey(RandomStringUtils.randomAlphanumeric(255))
                .certifyCd(certifyCd)
                .isUsed(false)
                .expireDatetime(LocalDateTime.now().plusMinutes(minutes))
                .build());
    }
}

package com.jsm.mm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsm.mm.domain.certify.Certify;
import com.jsm.mm.domain.certify.repository.CertifyRepository;
import com.jsm.mm.domain.dto.request.member.SignUpRequestDto;
import com.jsm.mm.domain.member.Member;
import com.jsm.mm.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class MemberApiControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CertifyRepository certifyRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @AfterEach
    void tearDown() {
        certifyRepository.deleteAllInBatch();
        memberRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("회원가입")
    void signUp() throws Exception {
        // given
        SignUpRequestDto signUpRequestDto = SignUpRequestDto.builder()
                .username("test123")
                .password("~test123")
                .email("test123@naver.com")
                .nickname("test123")
                .build();

        // when
        ResultActions actions = mvc.perform(post("/api/member/signUp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(signUpRequestDto)));

        Member member = memberRepository.findAll().get(0);
        Certify certify = certifyRepository.findAll().get(0);

        // then
        actions
                .andExpect(status().isOk());

        assertThat(member.getUsername()).isEqualTo(signUpRequestDto.getUsername());
        assertThat(passwordEncoder.matches(signUpRequestDto.getPassword(), member.getPassword())).isTrue();
        assertThat(member.getEmail()).isEqualTo(signUpRequestDto.getEmail());
        assertThat(member.getNickname()).isEqualTo(signUpRequestDto.getNickname());

        assertThat(certify.getMember().getId()).isEqualTo(member.getId());
    }
}
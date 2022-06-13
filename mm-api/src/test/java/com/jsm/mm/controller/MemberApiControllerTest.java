package com.jsm.mm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsm.mm.domain.certify.Certify;
import com.jsm.mm.domain.certify.repository.CertifyRepository;
import com.jsm.mm.domain.member.Member;
import com.jsm.mm.domain.member.enums.RoleName;
import com.jsm.mm.domain.member.repository.MemberRepository;
import com.jsm.mm.domain.memberlocation.MemberLocation;
import com.jsm.mm.domain.memberlocation.repository.MemberLocationRepository;
import com.jsm.mm.domain.memberrangelocation.repository.MemberRangeLocationRepository;
import com.jsm.mm.dto.request.member.SignUpRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("not-signUpEmail")
class MemberApiControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CertifyRepository certifyRepository;

    @Autowired
    private MemberLocationRepository memberLocationRepository;

    @Autowired
    private MemberRangeLocationRepository memberRangeLocationRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @AfterEach
    void tearDown() {
        memberRangeLocationRepository.deleteAllInBatch();
        memberLocationRepository.deleteAllInBatch();
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
                .location("31260110")
                .range("3")
                .build();

        // when
        ResultActions actions = mvc.perform(post("/api/member/signUp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(signUpRequestDto)));

        Member member = memberRepository.findAll().get(0);
        Certify certify = certifyRepository.findAll().get(0);
        MemberLocation memberLocation = memberLocationRepository.findAll().get(0);
        int memberRangeLocationSize = memberRangeLocationRepository.findAll().size();

        // then
        actions
                .andExpect(status().isOk());

        assertThat(member.getUsername()).isEqualTo(signUpRequestDto.getUsername());
        assertThat(passwordEncoder.matches(signUpRequestDto.getPassword(), member.getPassword())).isTrue();
        assertThat(member.getEmail()).isEqualTo(signUpRequestDto.getEmail());
        assertThat(member.getNickname()).isEqualTo(signUpRequestDto.getNickname());

        assertThat(certify.getMember().getId()).isEqualTo(member.getId());

        assertThat(memberLocation.getId().getMember().getId()).isEqualTo(member.getId());
        assertThat(memberLocation.getId().getLocation().getId()).isEqualTo(signUpRequestDto.getLocation());

        assertThat(memberRangeLocationSize).isGreaterThan(0);
    }

    @Test
    @DisplayName("계정 존재 여부 확인")
    void isExistMember() throws Exception {
        // given
        String column1 = "username";
        String value1 = "test123";
        String column2 = "email";
        String value2 = "test1234@naver.com";

        memberRepository.save(Member.builder()
                .username("test123")
                .password("~test123")
                .email("test123@naver.com")
                .nickname("test123")
                .isLeave(false)
                .roleName(RoleName.GUEST)
                .build());

        String url1 = String.format("/api/member/exist/%s?v=%s", column1, value1);
        String url2 = String.format("/api/member/exist/%s?v=%s", column2, value2);

        // when
        ResultActions actions1 = mvc.perform(get(url1));
        ResultActions actions2 = mvc.perform(get(url2));

        // then
        actions1
                .andExpect(status().isOk())
                .andExpect(content().string("false"));

        actions2
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}
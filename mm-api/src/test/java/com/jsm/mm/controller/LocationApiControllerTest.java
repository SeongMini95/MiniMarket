package com.jsm.mm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsm.mm.dto.request.location.AroundLocationRequestDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class LocationApiControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("현재 위치 기반 주변 지역 검색")
    void findAroundLocation() throws Exception {
        // given
        AroundLocationRequestDto aroundLocationRequestDto = AroundLocationRequestDto.builder()
                .x(127.00051086206544)
                .y(37.786574929740446)
                .build();

        // when
        ResultActions actions = mvc.perform(get("/api/location/around")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(aroundLocationRequestDto)));

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(Matchers.greaterThan(0)));
    }
}
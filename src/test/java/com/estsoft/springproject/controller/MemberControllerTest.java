package com.estsoft.springproject.controller;

import com.estsoft.springproject.entity.Member;
import com.estsoft.springproject.repository.MemberRepository;
import com.estsoft.springproject.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {
    @Autowired
    WebApplicationContext context;

    @Autowired
    MockMvc mockMvc;
    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    public void setUP(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Test
    public void testGetAllMember() throws Exception{
        //given 멤버목록 저장

        //when GET /members
        ResultActions resultActions = mockMvc.perform(get("/members")
                .accept(MediaType.APPLICATION_JSON));
        //then
        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));

    }
}
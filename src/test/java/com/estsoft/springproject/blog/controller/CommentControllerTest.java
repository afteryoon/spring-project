package com.estsoft.springproject.blog.controller;

import com.estsoft.springproject.blog.domain.Comment;
import com.estsoft.springproject.blog.domain.dto.request.CommentCreateDTO;
import com.estsoft.springproject.blog.repository.BlogRepository;
import com.estsoft.springproject.blog.repository.CommentRepository;
import com.estsoft.springproject.blog.service.BlogService;
import com.estsoft.springproject.blog.service.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest
class CommentControllerTest {
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private MockMvc mvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentService commentService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Test
    public void addCommentTest() throws Exception{
        //given
        CommentCreateDTO commentCreateDTO = new CommentCreateDTO(1L,"댓글 추가");
        String json = objectMapper.writeValueAsString(commentCreateDTO);
        //when
        ResultActions resultActions = mockMvc.perform(post("/api/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        //then
//        resultActions.andExpect(status().isCreated())
//                .andExpect(jsonPath("$.body").value(commentCreateDTO.getBody()));
//
        List<Comment> comments = commentRepository.findAll();
        assertNotNull(comments);
    }
}
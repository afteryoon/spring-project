package com.estsoft.springproject.blog.controller;

import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.domain.dto.AddArticleRequest;
import com.estsoft.springproject.blog.domain.dto.UpdateArticleRequest;
import com.estsoft.springproject.blog.service.BlogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BlogControllerTest2 {

    @InjectMocks
    private BlogController blogController;
    @Mock
    private BlogService blogService;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(blogController).build();
    }



    @Test
    @DisplayName("게시글 저장")
    public void test() throws Exception {
        //given
        String url = "/api/articles";
        String content = "test 내용";
        String title = "test 제목";
        // title content 가 있는 제이슨 데이터 필요
        AddArticleRequest request = new AddArticleRequest(title, content);
        ObjectMapper objectMapper = new ObjectMapper();
        String articlesJson = objectMapper.writeValueAsString(request);

        //stub
        Mockito.when(blogService.saveArticle(any()))
                .thenReturn(request.toEntity());

        //when
        ResultActions resultActions = mockMvc.perform(post(url)
                .content(articlesJson)
                .contentType(MediaType.APPLICATION_JSON));

        //then
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("title").value(title))
                .andExpect(jsonPath("content").value(content));


    }

    @Test
    @DisplayName("게시글 삭제")
    public void 게시글_삭제() throws Exception{
        //given
        String url = "/api/articles/{id}";
        Long id = 1L;
        //when
        ResultActions resultActions = mockMvc.perform(delete(url,id));

        //then
        resultActions.andExpect(status().isOk());
        verify(blogService,times(1)).deleteBy(id);
    }

    @Test
    @DisplayName("단건 조회")
    public void 게시글_단건_조회() throws Exception{
        //given
        String url = "/api/articles/{id}";
        Long id = 1L;
        String title = "title";
        String content = "content";
        Mockito.when(blogService.findById(any()))
                .thenReturn(getArticle(title,content,id));
        //when
        ResultActions resultActions = mockMvc.perform(get(url,id));
        //then
        resultActions.andExpect(status().isOk())
            .andExpect(jsonPath("title").value(title))
            .andExpect(jsonPath("content").value(content));

        verify(blogService,times(1)).findById(id);

    }

    @Test
    @DisplayName("게시글 수정")
    public void 게시글_수정() throws Exception{
        //given
        String url = "/api/articles/{id}";
        Long id = 1L;
        String title = "title";
        String content = "content";
        UpdateArticleRequest request = new UpdateArticleRequest(title, content);

        ObjectMapper objectMapper = new ObjectMapper();
        String updatedRequest = objectMapper.writeValueAsString(request);

        //stub
         Mockito.when(blogService.updateArticle(anyLong(),any(UpdateArticleRequest.class)))
                 .thenReturn(getArticle(title,content,id));

        //when
        ResultActions resultActions = mockMvc.perform(put(url,id)
                .content(updatedRequest)
                .contentType(MediaType.APPLICATION_JSON));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.content").value(content));

        verify(blogService,times(1)).updateArticle(anyLong(),any(UpdateArticleRequest.class));

    }


    private AddArticleRequest getAddArticleRequest(String title, String content) {
        return new AddArticleRequest(title, content);
    }

    private Article getArticle(String title,String content,Long id) {
        return Article.builder()
                .title(title)
                .content(content)
                .id(id)
                .build();
    }

}
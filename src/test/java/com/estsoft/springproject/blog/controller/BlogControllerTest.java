package com.estsoft.springproject.blog.controller;

import com.estsoft.springproject.blog.domain.dto.AddArticleRequest;
import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.domain.dto.UpdateArticleRequest;
import com.estsoft.springproject.blog.repository.BlogRepository;
import com.estsoft.springproject.blog.service.BlogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class BlogControllerTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private BlogService blogService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    //POST /articles API test
    @Test
    public void addArticle() throws Exception {
        //given: article 저장
        //Article article = new Article("제목","내용");
        AddArticleRequest request = new AddArticleRequest("제목","내용");
        //blogRepository.save(article);
        //직렬화 (object -> json)
        String json = objectMapper.writeValueAsString(request);

        //when: Post /articles API 호출
        ResultActions resultActions = mockMvc.perform(post("/articles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        //then: 호출 결과 검증
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(request.getTitle()))
                .andExpect(jsonPath("$.content").value(request.getContent()));

        List<Article> articleList = blogRepository.findAll();
        Assertions.assertThat(articleList.size()).isEqualTo(1);

    }

    //blog 게시글 조회
    @Test
    public void findAll() throws Exception{
        //given : 필요값 세팅
        Article article = blogRepository.save(new Article("title","content"));

        //when :: 조회 API
        ResultActions resultActions= mockMvc.perform(get("/articles")
                .accept(MediaType.APPLICATION_JSON));

        //then : 검증
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(article.getTitle()))
                .andExpect(jsonPath("$[0].content").value(article.getContent()));

    }

    //단건 조회
    @Test
    public void findById() throws Exception{
        //given
        Article article = blogRepository.save(new Article("title","content"));

        long id = article.getId();
        //long id = 22231L;
        //when
        ResultActions resultActions= mockMvc.perform(get("/articles/{id}",id)
            .accept(MediaType.APPLICATION_JSON));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(article.getTitle()))
                .andExpect(jsonPath("$.content").value(article.getContent()))
                .andExpect(jsonPath("$.id").value(id));
    }
    //단건 조회 400 케이스
    @Test
    public void findByIdF() throws Exception{
        //given
        long id = 9999L;
        //when
        ResultActions resultActions = mockMvc.perform(get("/articles/{id}",id)
        .accept(MediaType.APPLICATION_JSON));
        //then
        resultActions.andExpect(status().isBadRequest());
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class,
                () -> blogService.findById(id));

    }

    //단건 삭제
    @Test
    public void deleteById() throws Exception{
        //given
        Article article = blogRepository.save(new Article("title","content"));
        long id = article.getId();
        //when
        ResultActions resultActions = mockMvc.perform(delete("/articles/{id}",id)
        .accept(MediaType.APPLICATION_JSON));
        //then
        resultActions.andExpect(status().isOk());
//        Article deletedArticle = blogRepository.findById(id).orElse(null);
//        Assertions.assertThat(deletedArticle).isNull();
    }

    //PUT /articles/{id} body (json content)
    @Test
    public void updateArticle() throws Exception{
        Article article = blogRepository.save(new Article("title","content"));
        long id = article.getId();

        //given : 수정 데이터 (object) -> json
        UpdateArticleRequest request = new UpdateArticleRequest("title 변경","content 변경");
        String updateJsonContent = objectMapper.writeValueAsString(request);

        //when
        ResultActions resultActions= mockMvc.perform(put("/articles/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateJsonContent));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(request.getTitle()))
                .andExpect(jsonPath("$.content").value(request.getContent()))
                .andExpect(jsonPath("$.id").value(id));
    }

    //Update Error test
    @Test
    public void updateError() throws Exception{
        //given
        long id = 9999L;
        UpdateArticleRequest request = new UpdateArticleRequest("title 변경","컨테츠 변경");
        String updateJsonContent = objectMapper.writeValueAsString(request);
        //when
        ResultActions resultActions = mockMvc.perform(put("/articles/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateJsonContent));
        //then
        resultActions.andExpect(status().isBadRequest());
        assertThrows(
                IllegalArgumentException.class,
                () -> blogService.findById(id)
        );
    }

//    @Test
//    public void deleteArticleTest() throws Exception{
//        //given
//        Article article = blogRepository.save(new Article("title","content"));
//        Long id = article.getId();
//        //when
//        ResultActions resultActions = mockMvc.perform(delete("/articles/{id}",id)
//                .accept(MediaType.APPLICATION_JSON));
//        //then
//        resultActions.andExpect(status().isOk());
//        assertThrows(
//                IllegalArgumentException.class,
//                () -> blogService.findById(id)
//        );
//    }

}
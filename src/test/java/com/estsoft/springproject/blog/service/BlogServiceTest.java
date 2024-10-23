package com.estsoft.springproject.blog.service;

import com.estsoft.springproject.blog.domain.dto.AddArticleRequest;
import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.repository.BlogRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
class BlogServiceTest {
    @Autowired
    WebApplicationContext context;

    @Mock
    private BlogRepository blogRepository;

    @InjectMocks
    private BlogService blogService;

//    @BeforeEach
//    public void setUP(){
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//    }

    @Test
    public void testAddArticle() throws Exception{
        //given
        AddArticleRequest request = new AddArticleRequest("제목","내용");
        //when
        Article article = blogService.saveArticle(request);
        //then

    }
}
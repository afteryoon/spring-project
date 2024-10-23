package com.estsoft.springproject.blog.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArticleTest {

    @Test
    public void test() throws Exception{
        //given
        Article articleBuilder = Article.builder()
                .title("제목")
                .content("내용")
                .build();
        //when

        //then
        Assertions.assertThat(articleBuilder).isNotNull()
                .isInstanceOf(Article.class);
        Assertions.assertThat(articleBuilder.getTitle())
                .isEqualTo("제목");
    }

}
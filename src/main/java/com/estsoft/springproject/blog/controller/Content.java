package com.estsoft.springproject.blog.controller;

import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.domain.dto.AddArticleRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Content {
    private String title;
    private String body;

    @Override
    public String toString() {
        return "#title=" + title + ", body='" + body + '\'';
    }

    //convert
    public AddArticleRequest convertToArticleRequest() {
        return new AddArticleRequest(this.title,this.body);
    }
}

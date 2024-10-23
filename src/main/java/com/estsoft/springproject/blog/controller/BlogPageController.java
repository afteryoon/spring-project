package com.estsoft.springproject.blog.controller;

import com.estsoft.springproject.blog.domain.dto.ArticleResponse;
import com.estsoft.springproject.blog.domain.dto.ArticleViewResponse;
import com.estsoft.springproject.blog.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BlogPageController {
    private final BlogService blogService;

    public BlogPageController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/articles")
    String showArticle(Model model) {
        List<ArticleViewResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleViewResponse::new)
                .toList();

        model.addAttribute("articles",articles);
        return "articleList";
    }

    @GetMapping("articles/{id}")
    String showDetails(
            @PathVariable Long id, Model model
    ) {
        ArticleViewResponse articleResponse = new ArticleViewResponse( blogService.findById(id));
        System.out.println("**************************************"+articleResponse.getComments().toString());
        model.addAttribute("article",articleResponse);

        return "article";
    }

    //GET /new-articles?id=1
    @GetMapping("new-article")
    public String addArticle(
            @RequestParam(required = false) Long id
            ,Model model
    ){
        if (id == null) {
            model.addAttribute("article", new ArticleViewResponse());
        }else { //게시글 수정
            model.addAttribute("article",new ArticleViewResponse( blogService.findById(id)));
        }
        return "newArticle";
    }
}

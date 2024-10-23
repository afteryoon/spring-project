package com.estsoft.springproject.blog.domain.dto;

import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.domain.dto.response.CommentResponse;
import com.estsoft.springproject.blog.domain.dto.response.CommentResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//view 구성을 위한 DTO
@Getter @Setter
@NoArgsConstructor
public class ArticleViewResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private List<CommentResponse> comments;

    public ArticleViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
        this.comments = article.getComments().stream()
                .map(CommentResponse::new)
                .toList();
    }
}

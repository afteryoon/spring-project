package com.estsoft.springproject.blog.domain.dto;

import com.estsoft.springproject.blog.domain.Article;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "블로그 조회")
public class ArticleResponse {
    @Schema(description = "블로그 ID", type = "Long")
    private Long id;
    @Schema(description = "블로그 제목", type = "String")
    private String title;
    @Schema(description = "블로그 내용", type = "String")
    private String content;

    // 단일 Article을 ArticleResponse로 변환하는 정적 팩토리 메서드
    public static ArticleResponse entityToArticleResponse(Article article) {
        return ArticleResponse.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .build();
    }
}

package com.estsoft.springproject.blog.domain.dto.request;

import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentCreateDTO {

    private Long articleId;
    private String body;


    //convert method
    public Comment toComment(Article article) {
        return Comment.builder()
                .body(this.body)
                .article(article)
                .build();
    }
}

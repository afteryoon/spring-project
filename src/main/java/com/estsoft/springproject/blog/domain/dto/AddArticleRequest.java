package com.estsoft.springproject.blog.domain.dto;

import com.estsoft.springproject.blog.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddArticleRequest {
    private String title;
    private String content;

    //convert 메서드
    public Article toEntity(){
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }

    //출력 메서드
    @Override
    public String toString() {
        return "AddArticleRequest{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

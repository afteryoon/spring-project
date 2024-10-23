package com.estsoft.springproject.blog.domain;

import com.estsoft.springproject.blog.domain.dto.ArticleResponse;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @CreatedDate
    @Column
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column
    private LocalDateTime updatedAt;

    //연관관계 메서드
    @OneToMany(mappedBy = "article" , cascade = CascadeType.ALL ,orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setArticle(this);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
        comment.setArticle(null);
    }



    //생성 메서드
    @Builder
    public Article(String title, String content,long id) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

        public Article(String title, String content) {
            this.title = title;
            this.content = content;
        }

        public ArticleResponse convert() {
            return ArticleResponse.builder()
                    .id(this.getId())
                    .title(this.getTitle())
                    .content(this.getContent())
                    .build();
        }

        public void update(String title, String content) {
            this.title = title;
            this.content = content;
        }
}

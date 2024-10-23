package com.estsoft.springproject.blog.domain.dto.response;

import com.estsoft.springproject.blog.domain.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponse {
    private Long id;
    private String content;
    private LocalDateTime createdAt;

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getBody();
        this.createdAt = comment.getCreatedAt();
    }

    @Override
    public String toString() {
        return "CommentResponse{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}

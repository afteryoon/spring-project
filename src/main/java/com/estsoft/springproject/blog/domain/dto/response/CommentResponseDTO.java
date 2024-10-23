package com.estsoft.springproject.blog.domain.dto.response;

import com.estsoft.springproject.blog.domain.Comment;
import jakarta.persistence.Column;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDTO {
    private Long id;
    private String body;
    private LocalDateTime createdAt;

}

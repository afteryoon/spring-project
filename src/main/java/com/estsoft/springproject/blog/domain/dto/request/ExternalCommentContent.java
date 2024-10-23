package com.estsoft.springproject.blog.domain.dto.request;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExternalCommentContent {
    private Long postId;
    private Long id;
    private String name;
    private String email;
    private String body;
}

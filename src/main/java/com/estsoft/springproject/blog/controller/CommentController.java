package com.estsoft.springproject.blog.controller;

import com.estsoft.springproject.blog.domain.dto.request.CommentCreateDTO;
import com.estsoft.springproject.blog.domain.dto.response.CommentResponseDTO;
import com.estsoft.springproject.blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDTO> createComment(
            @RequestBody CommentCreateDTO request
                                                            ) {
        CommentResponseDTO createdComment = commentService.createdComment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<Page<CommentResponseDTO>> getComments(
            @PathVariable Long articleId,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ){
        Page<CommentResponseDTO> comments = commentService.getComments(articleId,pageable);
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponseDTO> updateComment(
            @RequestBody CommentCreateDTO request,
            @PathVariable Long commentId
    ){
        CommentResponseDTO res = commentService.updateComment(request,commentId);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommentResponseDTO> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }

}

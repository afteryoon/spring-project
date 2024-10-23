package com.estsoft.springproject.blog.service;

import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.domain.Comment;
import com.estsoft.springproject.blog.domain.dto.request.CommentCreateDTO;
import com.estsoft.springproject.blog.domain.dto.request.ExternalCommentContent;
import com.estsoft.springproject.blog.domain.dto.response.CommentResponseDTO;
import com.estsoft.springproject.blog.repository.BlogRepository;
import com.estsoft.springproject.blog.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;

    @Transactional
    public CommentResponseDTO createdComment(CommentCreateDTO commentCreateDTO) {
        Article article = blogRepository.findById(commentCreateDTO.getArticleId())
                .orElseThrow(() -> new IllegalArgumentException("Article not found"));
        Comment svaeComment = commentRepository.save(commentCreateDTO.toComment(article));

        return convertToDTO(svaeComment);
    }



    public Page<CommentResponseDTO> getComments(Long articleId, Pageable pageable) {
        Page<Comment> comments = commentRepository.findByArticleId(articleId,pageable);
        return comments.map(this::convertToDTO);
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    public CommentResponseDTO updateComment(CommentCreateDTO request, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        comment.updateBody(request.getBody());
        return convertToDTO(commentRepository.save(comment));
    }

    @Transactional
    public int insertExternalComment(List<ExternalCommentContent> body) {
        if (body == null || body.isEmpty()) {
            return 0;
        }

        List<Long> articleIds = body.stream()
                .map(ExternalCommentContent::getPostId)
                .distinct() // 중복 제거
                .toList();
        List<Article> articles = blogRepository.findAllById(articleIds);
        Map<Long,Article> articleMap = articles.stream()
                .collect(Collectors.toMap(Article::getId, article -> article));

        List<Comment> comments = body.stream()
                .map(content -> {
                    Article article = articleMap.get(content.getPostId());
                    if (article != null) {
                        return convertToComment(content, article);
                    }
                    return null;
                })
                .filter(Objects::nonNull) // null 값은 제외
                .toList();
        List<Comment> savedComments = commentRepository.saveAll(comments);
        return savedComments.size();
    }

    //convert method
    private Comment convertToComment(ExternalCommentContent content,Article article) {
        return Comment.builder()
                .article(article)
                .body(content.getBody().replace(" ",""))
                .build();
    }

    private CommentResponseDTO convertToDTO(Comment comment) {
//        return new CommentResponseDTO(
//                comment.getId(),
//                comment.getBody(),
//                comment.getCreatedAt()
        return CommentResponseDTO.builder()
                .id(comment.getId())
                .body(comment.getBody())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}

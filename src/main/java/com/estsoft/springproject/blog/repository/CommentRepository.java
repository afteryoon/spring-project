package com.estsoft.springproject.blog.repository;

import com.estsoft.springproject.blog.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByArticleId(Long articleId, Pageable pageable);
}

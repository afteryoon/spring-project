package com.estsoft.springproject.blog.repository;

import com.estsoft.springproject.blog.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Article, Long> {
    @Query("SELECT a FROM Article a LEFT JOIN FETCH a.comments WHERE a.id = :id")
    Optional<Article> findByIdWithComments(@Param("id")Long id);
}

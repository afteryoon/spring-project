package com.estsoft.springproject.blog.service;

import com.estsoft.springproject.blog.domain.dto.AddArticleRequest;
import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.domain.dto.ArticleResponse;
import com.estsoft.springproject.blog.domain.dto.ArticleViewResponse;
import com.estsoft.springproject.blog.domain.dto.UpdateArticleRequest;
import com.estsoft.springproject.blog.domain.dto.response.CommentResponseDTO;
import com.estsoft.springproject.blog.repository.BlogRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional
public class BlogService {
    final private BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    //bolg 게시글 저장
    public Article saveArticle(AddArticleRequest request) {
        //요청 txId: 1
        //log.warn("{} 반드시 서버에서 확인해야하는 값 : {}" , 1);
        return blogRepository.save(request.toEntity());
    }

    //blog 게시글 조회
    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    //단건 조회
    public Article findById(Long id) {
//        return blogRepository.findById(id)
//                .orElse(new Article());
//        return blogRepository.findById(id)
//                .orElseGet(Article::new);
        return  blogRepository.findByIdWithComments(id)
                .orElseThrow(() -> new IllegalArgumentException("not found id :"+ id));
    }

    //단건 삭제
    public void deleteBy(Long id) {
        blogRepository.deleteById(id);
    }

    //blog Update
    public Article updateArticle(long id,UpdateArticleRequest request) {
        Article article = findById(id);
        article.update(request.getTitle(), request.getContent());
        return article;
    }

    public List<Article> findAllById(List<Long> articleIds) {
        return blogRepository.findAllById(articleIds);
    }


//    //convert method
//    public ArticleViewResponse convertArticleViewResponse(Article article) {
//        return ArticleViewResponse.builder()
//                .id(article.getId())
//                .title(article.getTitle())
//                .content(article.getContent())
//                .comments(article.getComments().stream()
//                        .map(comment -> CommentResponseDTO::new)
//                )
//                .build();
//
//    }

}

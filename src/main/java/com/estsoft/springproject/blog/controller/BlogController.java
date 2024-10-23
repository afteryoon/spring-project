package com.estsoft.springproject.blog.controller;

import com.estsoft.springproject.blog.domain.dto.AddArticleRequest;
import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.domain.dto.ArticleResponse;
import com.estsoft.springproject.blog.domain.dto.UpdateArticleRequest;
import com.estsoft.springproject.blog.service.BlogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@Tag(name = "Blog CRUD", description = "API description : 작성 가능")
@RequestMapping("/api")
public class BlogController {
    final private BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    //@RequestMapping(method = RequestMethod.POST, name = "/articles")
    @PostMapping("/articles")
    public ResponseEntity<ArticleResponse> writeArticle(@RequestBody AddArticleRequest request) {
        //trace, debug, info, warn, error
//        log.debug("{}",request);
//        log.info("{}",request);
        //json
        Article article = blogService.saveArticle(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(article.convert()); //created == 201 ,ok == 200,201...
       // return ResponseEntity.ok(blogService.saveArticle(request));
    }

    //Request Mapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "100", description = "요청에 성공했습니다.", content = @Content(mediaType = "application/json"))
    })
    @Operation(summary = "블로그 전체 목록 보기", description = "블로그 메인화면에서 보여주는 전체목록")
    @GetMapping("/articles")
    public ResponseEntity<List<ArticleResponse>> findAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Principal principal = (Principal) authentication.getPrincipal();
//        List<ArticleResponse>  articleResponses = blogService.findAll().stream()
//                .map(article -> new ArticleResponse(article.getId(), article.getTitle(), article.getContent()))
//                .toList();
        List<ArticleResponse> articleResponseList =blogService.findAll().stream()
                .map(Article::convert)
                .toList();
        return ResponseEntity.ok(articleResponseList);
    }

    @Parameter(name = "id" , description = "블로그 글 ID" , example = "45")
    @GetMapping("/articles/{id}")
    public ResponseEntity<ArticleResponse> findById(@PathVariable Long id) {
        //ArticleResponse articleResponse = ArticleResponse.entityToArticleResponse(blogService.findById(blogId));
        Article article = blogService.findById(id);
        return ResponseEntity.ok(article.convert());
    }

    @DeleteMapping("/articles/{id}")
    public ResponseEntity<ArticleResponse> deleteById(@PathVariable Long id) {
        blogService.deleteBy(id);
        return ResponseEntity.ok().build();
    }

    //PUT : /articles/{id} 수정 API request body
    @PutMapping("/articles/{id}")
    public ResponseEntity<ArticleResponse> updateById(
            @PathVariable Long id,
            @RequestBody UpdateArticleRequest request
    ) {
        Article article = blogService.updateArticle(id, request);
        return ResponseEntity.ok(article.convert());
    }


    @ExceptionHandler(IllegalArgumentException.class) //Exception : 없는 번호일 경우
    private ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}

package com.estsoft.springproject.blog.controller;

import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.domain.dto.request.CommentCreateDTO;
import com.estsoft.springproject.blog.domain.dto.request.ExternalCommentContent;
import com.estsoft.springproject.blog.domain.dto.response.CommentResponse;
import com.estsoft.springproject.blog.domain.dto.response.CommentResponseDTO;
import com.estsoft.springproject.blog.service.BlogService;
import com.estsoft.springproject.blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ExternalApiController {

    private final BlogService blogService;
    private final CommentService commentService;

    @GetMapping("/api/external")
    public String callApi() {
        //외부 API 호출
        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> json = restTemplate.getForEntity(
//                "https://jsonplaceholder.typicode.com/posts", String.class);
//        log.info("statusCode: {}",json.getStatusCode());
//        log.info("statusText: {}",json.getBody());
        //역직렬화 필요 (json -> object)

        String url = "https://jsonplaceholder.typicode.com/posts";
        //역질렬화 (restTemplate)
        ResponseEntity<List<Content>> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, null,new ParameterizedTypeReference<List<Content>>(){});
        log.info("statusCode: {}",responseEntity.getStatusCode());
        log.info("{}",responseEntity.getBody());

        List<Content> body = responseEntity.getBody();
        if(body != null) {
            responseEntity.getBody().stream()
                    .map(Content::convertToArticleRequest)
                    .forEach(blogService::saveArticle);
        }else {
            log.warn("Response body is null");
            throw new IllegalStateException("ExternalApiController.callApi:Response body is null");
        }

        return "ok";
    }

    @GetMapping("/api/external/comments")
    public ResponseEntity<CommentResponse> callApiComments() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/comments";
        ResponseEntity<List<ExternalCommentContent>> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, null,new ParameterizedTypeReference<>() {});

        List<ExternalCommentContent> body = responseEntity.getBody();
        if(body != null) {
           int insertCount = commentService.insertExternalComment(body);
           log.info("insertCount: {}",insertCount);
        }

        return ResponseEntity.ok().build();
    }
}

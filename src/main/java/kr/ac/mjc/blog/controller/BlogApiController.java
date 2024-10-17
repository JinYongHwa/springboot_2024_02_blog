package kr.ac.mjc.blog.controller;

import kr.ac.mjc.blog.domain.Article;
import kr.ac.mjc.blog.dto.ArticleRequest;
import kr.ac.mjc.blog.dto.ArticleResponse;
import kr.ac.mjc.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BlogApiController {

    @Autowired
    ArticleService articleService;

    @PostMapping("/api/article")
    public ResponseEntity<ArticleResponse> writeArticle(@RequestBody ArticleRequest articleRequest){
        ArticleResponse response=articleService.writeArticle(articleRequest);

        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/api/article/{id}")
    public ResponseEntity<ArticleResponse> deleteArticle(@PathVariable("id") long id){
        ArticleResponse response=articleService.deleteArticle(id);
        return ResponseEntity.ok(response);
    }

}

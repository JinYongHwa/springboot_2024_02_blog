package kr.ac.mjc.blog.controller;

import jakarta.servlet.http.HttpSession;
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
    public ResponseEntity<ArticleResponse> writeArticle(@RequestBody ArticleRequest articleRequest, HttpSession session){
        String loginUserId=(String)session.getAttribute("loginUserId");
        if(loginUserId==null){  //로그인 된 사용자가 없을때 -> 글작성불가
            ArticleResponse response=new ArticleResponse();
            response.setSuccess(false);
            response.setMessage("로그인 후 이용가능합니다");
            return ResponseEntity.ok(response);
        }
        else{   //로그인이 되있을때 -> 글작성가능
            ArticleResponse response=articleService.writeArticle(articleRequest,loginUserId);
            return ResponseEntity.ok(response);
        }

    }
    @DeleteMapping("/api/article/{id}")
    public ResponseEntity<ArticleResponse> deleteArticle(@PathVariable("id") long id){
        ArticleResponse response=articleService.deleteArticle(id);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/api/article/{id}")
    public ResponseEntity<ArticleResponse> modifyArticle(@PathVariable("id") long id,
                                                         @RequestBody ArticleRequest articleRequest){
        ArticleResponse response=articleService.modifyArticle(id,articleRequest);
        return ResponseEntity.ok(response);
    }

}

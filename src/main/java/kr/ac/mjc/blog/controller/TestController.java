package kr.ac.mjc.blog.controller;

import kr.ac.mjc.blog.domain.Article;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class TestController {

    @GetMapping("/test")
    public String test(Model model){
        Article article=new Article();
        article.setId(1l);
        article.setTitle("글제목");
        article.setContent("글내용");
        model.addAttribute("article",article);

        ArrayList<Article> articles=new ArrayList();
        for(int i=0;i<100;i++){
            Article a=new Article();
            a.setTitle("글제목"+i);
            a.setContent("글내용"+i);
            articles.add(a);
        }
        model.addAttribute("articles",articles);
        return "test";
    }

    @GetMapping("/test2")
    public String test2(Model model){
        Article article=new Article();
        article.setId(1l);
        article.setTitle("글제목");
        article.setContent("글내용");
        model.addAttribute("article",article);

        ArrayList<Article> articles=new ArrayList();
        for(int i=0;i<100;i++){
            Article a=new Article();
            a.setTitle("글제목"+i);
            a.setContent("글내용"+i);
            articles.add(a);
        }
        model.addAttribute("articles",articles);
        return "test2";
    }
}

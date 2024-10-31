package kr.ac.mjc.blog.controller;

import kr.ac.mjc.blog.domain.Article;
import kr.ac.mjc.blog.dto.ArticleRequest;
import kr.ac.mjc.blog.dto.ArticleResponse;
import kr.ac.mjc.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BlogViewController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/")
    public ModelAndView main(){
        ModelAndView mav=new ModelAndView();
        List<Article> articles=articleService.getArticles();
        mav.addObject("articles",articles);
        mav.setViewName("main");
        return mav;
    }
    @GetMapping("/write/article")
    public String write(){
        return "write";
    }
    @PostMapping("/article/write")
    public ModelAndView articleWrite(@ModelAttribute ArticleRequest articleRequest){
        ArticleResponse response=articleService.writeArticle(articleRequest);
        ModelAndView mav=new ModelAndView();
        mav.addObject("article",response.getArticle());
        mav.setViewName("redirect:/article/"+response.getArticle().getId());
        return mav;
    }
    @GetMapping("/article/{id}")
    public ModelAndView view(@PathVariable(name="id") Long id){
        Article article=articleService.getItem(id);
        ModelAndView mav=new ModelAndView();
        mav.addObject("article",article);
        mav.setViewName("view");
        return mav;
    }

    @GetMapping("/article/modify/{id}")
    public ModelAndView modify(@PathVariable(name="id")Long id){
        Article article=articleService.getItem(id);
        ModelAndView mav=new ModelAndView();
        mav.addObject("article",article);
        mav.setViewName("modify");
        return mav;
    }

}

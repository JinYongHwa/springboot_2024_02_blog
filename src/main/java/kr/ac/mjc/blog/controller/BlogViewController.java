package kr.ac.mjc.blog.controller;

import jakarta.servlet.http.HttpSession;
import kr.ac.mjc.blog.domain.Article;
import kr.ac.mjc.blog.domain.Category;
import kr.ac.mjc.blog.dto.ArticleRequest;
import kr.ac.mjc.blog.dto.ArticleResponse;
import kr.ac.mjc.blog.service.ArticleService;
import kr.ac.mjc.blog.service.CategoryService;
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

    @Autowired
    CategoryService categoryService;

    @GetMapping("/")
    public ModelAndView main(HttpSession session){
        ModelAndView mav=new ModelAndView();
        List<Article> articles=articleService.getArticles();
        mav.addObject("articles",articles);
        if(session.getAttribute("loginUserId")!=null){  //로그인이 되어있으면 로그인 ID View 에 보냄
            mav.addObject("loginUserId",session.getAttribute("loginUserId"));
        }
        mav.setViewName("main");
        return mav;
    }
    @GetMapping("/write/article")
    public ModelAndView write(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("write");

        List<Category> categoryList=categoryService.getCategoryList();
        mav.addObject("categoryList",categoryList);
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
    @GetMapping("/login")
    public String loginView(){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginUserId");
        return "redirect:/";
    }


}

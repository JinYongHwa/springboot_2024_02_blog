package kr.ac.mjc.blog.service;

import kr.ac.mjc.blog.domain.Article;
import kr.ac.mjc.blog.dto.ArticleRequest;
import kr.ac.mjc.blog.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    public List<Article> getArticles(){
        return articleRepository.findAll();
    }
    
    //새로운 글 작성
    public Article writeArticle(ArticleRequest articleRequest){
        Article article=new Article();
        article.setTitle(articleRequest.getTitle());
        article.setContent(articleRequest.getContent());
        article=articleRepository.save(article);
        return article;
    }

    //글한개 가져오기
    public Article getItem(long id){
        Optional<Article> result=articleRepository.findById(id);
        if(result.isEmpty()){
            return null;
        }
        else{
            return result.get();
        }
    }

}

package kr.ac.mjc.blog.service;

import kr.ac.mjc.blog.domain.Article;
import kr.ac.mjc.blog.dto.ArticleRequest;
import kr.ac.mjc.blog.dto.ArticleResponse;
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
    public ArticleResponse writeArticle(ArticleRequest articleRequest){
        System.out.println(articleRequest.getTitle());
        System.out.println(articleRequest.getContent());

        ArticleResponse response=new ArticleResponse();
        Article article=new Article();
        article.setTitle(articleRequest.getTitle());
        article.setContent(articleRequest.getContent());
        if(article.getTitle().indexOf("욕설")>-1){
            response.setSuccess(false);
            response.setMessage("제목에 욕설이 들어가면 안됩니다");
            return response;
        }

        article=articleRepository.save(article);
        response.setSuccess(true);
        response.setArticle(article);
        return response;
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

    //글삭제
    public ArticleResponse deleteArticle(long id){
        Optional<Article> result=articleRepository.findById(id);
        ArticleResponse response=new ArticleResponse();
        if(result.isEmpty()){   //해당 글 ID 에대한 article 이 없는경우
            response.setSuccess(false);
            response.setMessage("존재하지 않는 글입니다");
        }
        else{   //해당 id 로 글이 있는경우
            articleRepository.deleteById(id);
            response.setSuccess(true);
        }
        return response;

    }

}

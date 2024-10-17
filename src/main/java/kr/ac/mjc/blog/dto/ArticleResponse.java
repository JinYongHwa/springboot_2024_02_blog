package kr.ac.mjc.blog.dto;


import kr.ac.mjc.blog.domain.Article;

public class ArticleResponse {

    private boolean success;        //요청에 대한 성공여부
    private Article article;        //요청 처리된 Article 객체
    private String message;         //실패시 메세지

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

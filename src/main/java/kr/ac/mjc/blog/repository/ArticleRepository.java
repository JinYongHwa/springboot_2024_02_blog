package kr.ac.mjc.blog.repository;

import kr.ac.mjc.blog.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Long> {
}

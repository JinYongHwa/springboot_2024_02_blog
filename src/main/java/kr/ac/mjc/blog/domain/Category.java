package kr.ac.mjc.blog.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="name",nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "article_category",  // 중간 테이블 이름
            joinColumns = @JoinColumn(name = "category_id"),  // Student 엔티티의 컬럼
            inverseJoinColumns = @JoinColumn(name = "article_id")  // Course 엔티티의 컬럼
    )
    List<Article> articleList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

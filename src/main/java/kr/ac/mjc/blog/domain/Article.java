package kr.ac.mjc.blog.domain;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class Article {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title",nullable = false)
    private String title;

    @Column(name="content",nullable = false)
    private String content;

    @ManyToOne
    private User writeUser;

    @ManyToMany
    @JoinTable(
            name = "article_category",  // 중간 테이블 이름
            joinColumns = @JoinColumn(name = "article_id"),  // Student 엔티티의 컬럼
            inverseJoinColumns = @JoinColumn(name = "category_id")  // Course 엔티티의 컬럼
    )
    private List<Category> categoryList;


    @CreatedDate
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getWriteUser() {
        return writeUser;
    }

    public void setWriteUser(User writeUser) {
        this.writeUser = writeUser;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public String getFormattedWriteDate(){
        if(this.createdAt!=null){
            return this.createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
        else{
            return "";
        }

    }
}

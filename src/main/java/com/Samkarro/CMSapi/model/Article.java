package com.Samkarro.CMSapi.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int articleId;
    private String title;
    @Lob
    private String body;
    @ManyToMany
            @JoinTable(
                    name = "article_category_ownership",
                    joinColumns = {@JoinColumn(name = "article_id")},
                    inverseJoinColumns = {@JoinColumn(name = "category_id")}
            )
    Set<Category> categories = new HashSet<>();

    @JsonCreator
    public Article() {}

    public Article(int articleId, String title, String body, Set<Category> categories) {
        this.articleId = articleId;
        this.title = title;
        this.body = body;
        this.categories = categories;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}

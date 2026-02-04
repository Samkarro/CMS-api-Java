package com.Samkarro.CMSapi.repository;

import com.Samkarro.CMSapi.model.Article;
import com.Samkarro.CMSapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    @Query("SELECT a FROM Article a WHERE :category MEMBER OF a.categories")
    List<Article> findAllByCategories(@Param("category") Category category);
}

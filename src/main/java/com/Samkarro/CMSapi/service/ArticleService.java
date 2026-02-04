package com.Samkarro.CMSapi.service;

import com.Samkarro.CMSapi.model.Article;
import com.Samkarro.CMSapi.model.Category;
import com.Samkarro.CMSapi.repository.ArticleRepository;
import com.Samkarro.CMSapi.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ArticleService {

    private final ArticleRepository articleRepo;
    private final CategoryRepository categoryRepo;

    public ArticleService(ArticleRepository articleRepo,
                          CategoryRepository categoryRepo) {
        this.articleRepo = articleRepo;
        this.categoryRepo = categoryRepo;
    }

    public List<Article> ListArticles() {
        return this.articleRepo.findAll();
    }

    public Article Create(Article article) {

        Set<Category> resolvedCategories = new HashSet<>();

        for (Category incoming : article.getCategories()) {

            Category managedCategory = categoryRepo
                    .findByName(incoming.getName())
                    .orElseGet(() ->
                            categoryRepo.save(new Category(incoming.getName()))
                    );

            resolvedCategories.add(managedCategory);
        }

        article.setCategories(resolvedCategories);

        return articleRepo.save(article);
    }
}

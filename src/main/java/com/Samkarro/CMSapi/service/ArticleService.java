package com.Samkarro.CMSapi.service;

import com.Samkarro.CMSapi.model.Article;
import com.Samkarro.CMSapi.model.Category;
import com.Samkarro.CMSapi.repository.ArticleRepository;
import com.Samkarro.CMSapi.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public Article GetById(int id) {
        var returnedArticle = this.articleRepo.findById(id).orElse(null);

        if(returnedArticle != null) return returnedArticle;
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Article with given ID not found");
    }

    public void Delete(int id) {
        this.articleRepo.deleteById(id);
    }

    public Article Update(Article updatedArticle, int id) {
        var existingArticle = this.articleRepo.findById(id).orElse(null);

        if(existingArticle == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Article to update with given ID not found");
        }

        Set<Category> resolvedCategories = new HashSet<>();

        for (Category incoming : updatedArticle.getCategories()) {

            Category managedCategory = categoryRepo
                    .findByName(incoming.getName())
                    .orElseGet(() ->
                            categoryRepo.save(new Category(incoming.getName()))
                    );

            resolvedCategories.add(managedCategory);
        }

        existingArticle.setCategories(resolvedCategories);
        existingArticle.setBody(updatedArticle.getBody());
        existingArticle.setTitle(updatedArticle.getTitle());

        return articleRepo.save(existingArticle);
    }
}

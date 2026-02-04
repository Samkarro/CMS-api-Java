package com.Samkarro.CMSapi.service;

import com.Samkarro.CMSapi.model.Article;
import com.Samkarro.CMSapi.model.Category;
import com.Samkarro.CMSapi.repository.ArticleRepository;
import com.Samkarro.CMSapi.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepo;
    private final ArticleRepository articleRepo;

    public CategoryService(CategoryRepository categoryRepo, ArticleRepository articleRepo){
        this.categoryRepo = categoryRepo;
        this.articleRepo = articleRepo;
    }

    public List<Category> ListCategories(){
        return this.categoryRepo.findAll();
    }

    public Category Create(Category category) {
        return this.categoryRepo.save(category);
    }

    @Transactional
    public void Delete(int id) {
        var category = this.categoryRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        List<Article> articlesWithCategory = this.articleRepo.findAllByCategories(category);

        for (Article article : articlesWithCategory) {
            article.getCategories().remove(category);
        }

        this.articleRepo.saveAll(articlesWithCategory);
        this.categoryRepo.delete(category);
    }
}

package com.Samkarro.CMSapi.service;

import com.Samkarro.CMSapi.model.Category;
import com.Samkarro.CMSapi.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository){
        this.repository = repository;
    }

    public List<Category> ListCategories(){
        return this.repository.findAll();
    }

    public Category Create(Category category) {
        return this.repository.save(category);
    }

    public void Delete(int id) {
        var forDeletion = this.repository.findById(id).orElse(null);
        if(forDeletion == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category with provided id not found");
        }
        this.repository.deleteById(id);
    }
}

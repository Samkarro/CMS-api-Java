package com.Samkarro.CMSapi.service;

import com.Samkarro.CMSapi.model.Category;
import com.Samkarro.CMSapi.repository.CategoryRepository;
import org.springframework.stereotype.Service;

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
        System.out.println(category);
        return this.repository.save(category);
    }
}

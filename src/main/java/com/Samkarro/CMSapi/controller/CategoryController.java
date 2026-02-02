package com.Samkarro.CMSapi.controller;

import com.Samkarro.CMSapi.model.Category;
import com.Samkarro.CMSapi.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service){
        this.service = service;
    }

    @GetMapping
    public List<Category> ListCategories(){
        return this.service.ListCategories();
    }

    @PostMapping
    public Category Create(@RequestBody Category category){
        return this.service.Create(category);
    }

    @DeleteMapping("/{id}")
    public void Delete(@PathVariable int id){
        this.service.Delete(id);
    }
}

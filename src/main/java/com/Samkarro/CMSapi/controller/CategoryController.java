package com.Samkarro.CMSapi.controller;

import com.Samkarro.CMSapi.model.Category;
import com.Samkarro.CMSapi.service.CategoryService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service){
        this.service = service;
    }

    @GetMapping("/")
    public List<Category> ListCategories(){
        return this.service.ListCategories();
    }

}

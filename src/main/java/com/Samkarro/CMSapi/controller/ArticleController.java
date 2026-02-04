package com.Samkarro.CMSapi.controller;

import com.Samkarro.CMSapi.common.DTO.ArticleResponse;
import com.Samkarro.CMSapi.model.Article;
import com.Samkarro.CMSapi.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/article")
public class ArticleController {

    private final ArticleService service;

    public ArticleController(ArticleService service) {
        this.service = service;
    }

    @GetMapping
    public List<Article> ListArticles(){
        return this.service.ListArticles();
    }

    @GetMapping("/{id}")
    public Article GetById(@PathVariable int id) {
        return this.service.GetById(id);
    }

    @PostMapping
    public Article Create(@RequestBody Article article){
        return this.service.Create(article);
    }
}

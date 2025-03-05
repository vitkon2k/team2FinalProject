package com.web.finalproject.api;

import com.web.finalproject.entity.NewsEntity;
import com.web.finalproject.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/news")
public class NewsApi {

    @Autowired
    private NewsService newsService;

    @GetMapping
    public List<NewsEntity> getAllNews() {
        return newsService.getAllNews();
    }

    @GetMapping("/{id}")
    public Optional<NewsEntity> getNewsById(@PathVariable Integer id) {
        return newsService.getNewsById(id);
    }

    @PostMapping
    public NewsEntity createNews(@RequestBody NewsEntity newsEntity) {
        return newsService.createNews(newsEntity);
    }

    @PutMapping("/{id}")
    public NewsEntity updateNews(@PathVariable Integer id, @RequestBody NewsEntity newsEntityDetails) {
        return newsService.updateNews(id, newsEntityDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteNews(@PathVariable Integer id) {
        newsService.deleteNews(id);
    }
}


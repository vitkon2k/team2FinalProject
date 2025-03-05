package com.web.finalproject.service;

import com.web.finalproject.entity.NewsEntity;

import java.util.List;
import java.util.Optional;

public interface NewsService {
    List<NewsEntity> getAllNews();
    void deleteNews(Integer id);
    Optional<NewsEntity> getNewsById(Integer id);
    NewsEntity createNews(NewsEntity newsEntity);
    NewsEntity updateNews(Integer id, NewsEntity newsEntityDetails);
}

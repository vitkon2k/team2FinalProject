package com.web.finalproject.service.impl;

import com.web.finalproject.entity.NewsEntity;
import com.web.finalproject.repository.NewsRepository;
import com.web.finalproject.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public List<NewsEntity> getAllNews() {
        return newsRepository.findAll();
    }

    @Override
    public Optional<NewsEntity> getNewsById(Integer id) {
        return newsRepository.findById(id);
    }

    @Override
    public NewsEntity createNews(NewsEntity newsEntity) {
        return newsRepository.save(newsEntity);
    }

    @Override
    public NewsEntity updateNews(Integer id, NewsEntity newsEntityDetails) {
        return newsRepository.findById(id).map(newsEntity -> {
            newsEntity.setTitle(newsEntityDetails.getTitle());
            newsEntity.setContent(newsEntityDetails.getContent());
            newsEntity.setImage_url(newsEntityDetails.getImage_url());
            newsEntity.setAuthor_id(newsEntityDetails.getAuthor_id());
            return newsRepository.save(newsEntity);
        }).orElse(null);
    }

    @Override
    public void deleteNews(Integer id) {
        newsRepository.deleteById(id);
    }
}


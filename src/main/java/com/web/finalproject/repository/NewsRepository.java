package com.web.finalproject.repository;

import com.web.finalproject.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, Integer> {
}

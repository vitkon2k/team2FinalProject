package com.web.finalproject.service;

import com.web.finalproject.entity.CategoryEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    CategoryEntity create(CategoryEntity category);
    CategoryEntity update(Integer id, CategoryEntity category);
    void delete(Integer id);
//    CategoryEntity findByName(String categoryName);
    Optional<CategoryEntity> findById(Integer id);
    List<CategoryEntity> findAll();
}

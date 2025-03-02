package com.web.finalproject.service.impl;

import com.web.finalproject.entity.CategoryEntity;
import com.web.finalproject.repository.CategoryRepository;
import com.web.finalproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryEntity create(CategoryEntity category) {
        return categoryRepository.save(category);
    }

    @Override
    public CategoryEntity update(Integer id, CategoryEntity category) {
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(category.getId());
        if (categoryEntity.isPresent()) {
            CategoryEntity categoryEntity1 = categoryRepository.getOne(category.getId());
            categoryEntity1.setName(category.getName());
            categoryEntity1.setDescription(category.getDescription());
            return categoryRepository.save(categoryEntity1);
        }
        throw new RuntimeException("Không tìm thấy danh mục này");
    }

    @Override
    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }

//    @Override
//    public CategoryEntity findByName(String categoryName) {
//
//    }

    @Override
    public List<CategoryEntity> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<CategoryEntity> findById(Integer id) {
        return categoryRepository.findById(id);
    }
}

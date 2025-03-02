package com.web.finalproject.service;

import com.web.finalproject.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductEntity create(ProductEntity product);
    ProductEntity update(Integer id, ProductEntity product);
    void delete(Integer id);
    List<ProductEntity> findAll();
    Optional<ProductEntity> getById(Integer id);
}

package com.web.finalproject.service.impl;

import com.web.finalproject.entity.ProductEntity;
import com.web.finalproject.repository.ProductRepository;
import com.web.finalproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductEntity create(ProductEntity product) {
        return productRepository.save(product);
    }

    @Override
    public ProductEntity update(Integer id, ProductEntity product) {
        Optional<ProductEntity> productEntity = productRepository.findById(product.getId());
        if (productEntity.isPresent()) {
            ProductEntity productEntity1 = productRepository.getOne(product.getId());
            productEntity1.setName(product.getName());
            productEntity1.setDescription(product.getDescription());
            productEntity1.setPrice(product.getPrice());
            productEntity1.setDiscountPrice(product.getDiscountPrice());
            productEntity1.setImageUrl(product.getImageUrl());
            productEntity1.setCategory(product.getCategory());
            return productRepository.save(productEntity1);
        }
        throw new RuntimeException("Sản phẩm không tồn tại ");
    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<ProductEntity> getById(Integer id) {
        return productRepository.findById(id);
    }

}

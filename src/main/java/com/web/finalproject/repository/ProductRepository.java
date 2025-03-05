package com.web.finalproject.repository;

import com.web.finalproject.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    List<ProductEntity> findByBrandId(Integer brandId);

    List<ProductEntity> findByCategoryId(Integer categoryId);
}

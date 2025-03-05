package com.web.finalproject.service;

import com.web.finalproject.entity.BrandEntity;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    List<BrandEntity> getAllBrands();
    Optional<BrandEntity> getBrandById(Integer id);
    BrandEntity createBrand(BrandEntity brandEntity);
    BrandEntity updateBrand(Integer id, BrandEntity brandEntityDetails);
    void deleteBrand(Integer id);
}

package com.web.finalproject.service.impl;

import com.web.finalproject.entity.BrandEntity;
import com.web.finalproject.repository.BrandRepository;
import com.web.finalproject.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {


    @Autowired
    private BrandRepository brandRepository;

    @Override
    public List<BrandEntity> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Optional<BrandEntity> getBrandById(Integer id) {
        return brandRepository.findById(id);
    }

    @Override
    public BrandEntity createBrand(BrandEntity brandEntity) {
        return brandRepository.save(brandEntity);
    }

    @Override
    public BrandEntity updateBrand(Integer id, BrandEntity brandEntityDetails) {
        return brandRepository.findById(id).map(brandEntity -> {
            brandEntity.setName(brandEntityDetails.getName());
            brandEntity.setImage_url(brandEntityDetails.getImage_url());
            return brandRepository.save(brandEntity);
        }).orElse(null);
    }

    @Override
    public void deleteBrand(Integer id) {
        brandRepository.deleteById(id);
    }
}


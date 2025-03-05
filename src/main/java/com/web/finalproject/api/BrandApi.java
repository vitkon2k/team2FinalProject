package com.web.finalproject.api;

import com.web.finalproject.entity.BrandEntity;
import com.web.finalproject.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/brands")
public class BrandApi {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public List<BrandEntity> getAllBrands() {
        return brandService.getAllBrands();
    }

    @GetMapping("/{id}")
    public Optional<BrandEntity> getBrandById(@PathVariable Integer id) {
        return brandService.getBrandById(id);
    }

    @PostMapping
    public BrandEntity createBrand(@RequestBody BrandEntity brandEntity) {
        return brandService.createBrand(brandEntity);
    }

    @PutMapping("/{id}")
    public BrandEntity updateBrand(@PathVariable Integer id, @RequestBody BrandEntity brandEntityDetails) {
        return brandService.updateBrand(id, brandEntityDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteBrand(@PathVariable Integer id) {
        brandService.deleteBrand(id);
    }
}
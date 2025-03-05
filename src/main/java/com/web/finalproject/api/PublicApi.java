package com.web.finalproject.api;

import com.web.finalproject.entity.BrandEntity;
import com.web.finalproject.entity.CategoryEntity;
import com.web.finalproject.entity.NewsEntity;
import com.web.finalproject.entity.ProductEntity;
import com.web.finalproject.service.BrandService;
import com.web.finalproject.service.CategoryService;
import com.web.finalproject.service.NewsService;
import com.web.finalproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicApi {

    @Autowired
    private ProductService productService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/product")
    public ResponseEntity<List<ProductEntity>> getAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductEntity> getById(@PathVariable int id) {
        return productService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/product/brand/{brandId}")
    public ResponseEntity<List<ProductEntity>> getByBrandId(@PathVariable int brandId) {
        List<ProductEntity> products = productService.getByBrand(brandId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/product/category/{categoryId}")
    public ResponseEntity<List<ProductEntity>> getByCategoryId(@PathVariable int categoryId) {
        List<ProductEntity> products = productService.getByCategory(categoryId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/brand")
    public ResponseEntity<List<BrandEntity>> getAllBrand() {
        return ResponseEntity.ok(brandService.getAllBrands());
    }

    @GetMapping("/brand/{id}")
    public ResponseEntity<BrandEntity> getBrandById(@PathVariable int id) {
        return brandService.getBrandById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/news")
    public ResponseEntity<List<NewsEntity>> getAllNews() {
        return ResponseEntity.ok(newsService.getAllNews());
    }

    @GetMapping("/news/{id}")
    public ResponseEntity<NewsEntity> getNewsById(@PathVariable int id) {
        return newsService.getNewsById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category")
    public ResponseEntity<List<CategoryEntity>> getAllCategory() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryEntity> getCategoryById(@PathVariable int id) {
        return categoryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

package com.web.finalproject.api;

import com.web.finalproject.entity.ProductEntity;
import com.web.finalproject.entity.CategoryEntity;
import com.web.finalproject.entity.BrandEntity;
import com.web.finalproject.service.ProductService;
import com.web.finalproject.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api/admin/product")
public class ProductApi {

    @Autowired
    private ProductService productService;

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping
    public ResponseEntity<List<ProductEntity>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<ProductEntity> create(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("categoryId") CategoryEntity categoryId,
            @RequestParam("brandId") BrandEntity brandId,
            @RequestParam("price") Double price,
            @RequestParam("discountPrice") Double discountPrice,
            @RequestParam("stock") Integer stock,
            @RequestPart(value = "image", required = false) MultipartFile image) {

        String imageUrl = (image != null) ? fileStorageService.save(image) : null;
        ProductEntity newProduct = new ProductEntity();
        newProduct.setName(name);
        newProduct.setDescription(description);
        newProduct.setCategory(categoryId);
        newProduct.setBrand(brandId);
        newProduct.setPrice(price);
        newProduct.setDiscountPrice(discountPrice);
        newProduct.setStock(stock);
        newProduct.setImageUrl(imageUrl);

        return ResponseEntity.ok(productService.create(newProduct));
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<ProductEntity> update(
            @PathVariable Integer id,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("categoryId") CategoryEntity categoryId,
            @RequestParam("brandId") BrandEntity brandId,
            @RequestParam("price") Double price,
            @RequestParam("discountPrice") Double discountPrice,
            @RequestParam("stock") Integer stock,
            @RequestPart(value = "image", required = false) MultipartFile image) {

        ProductEntity existingProduct = productService.getById(id).orElse(null);
        if (existingProduct == null) {
            return ResponseEntity.notFound().build();
        }

        if (image != null && !image.isEmpty()) {
            String imageUrl = fileStorageService.save(image);
            existingProduct.setImageUrl(imageUrl);
        } else {
            existingProduct.setImageUrl(existingProduct.getImageUrl());
        }

        existingProduct.setName(name);
        existingProduct.setDescription(description);
        existingProduct.setCategory(categoryId);
        existingProduct.setBrand(brandId);
        existingProduct.setPrice(price);
        existingProduct.setDiscountPrice(discountPrice);
        existingProduct.setStock(stock);

        return ResponseEntity.ok(productService.update(id, existingProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> getById(@PathVariable Integer id) {
        return productService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
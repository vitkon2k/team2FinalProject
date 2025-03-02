package com.web.finalproject.api;

import com.web.finalproject.entity.CategoryEntity;
import com.web.finalproject.entity.ProductEntity;
import com.web.finalproject.service.CategoryService;
import com.web.finalproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/category")
public class CategoryApi {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<CategoryEntity> create(@RequestBody CategoryEntity categoryEntity) {
        return ResponseEntity.ok(categoryService.create(categoryEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryEntity> update(@RequestBody CategoryEntity categoryEntity, @PathVariable Integer id) {
        return ResponseEntity.ok(categoryService.update(id, categoryEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryEntity> delete(@PathVariable Integer id) {
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<CategoryEntity>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

}

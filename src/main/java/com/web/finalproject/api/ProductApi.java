package com.web.finalproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductApi {

    @GetMapping
    public String get() {
        return "Return list of products";
    }
}

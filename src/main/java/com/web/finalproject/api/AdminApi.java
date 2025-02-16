package com.web.finalproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminApi {
    @GetMapping
    public String admin() {
        return "admin/dashboard";
    }
}

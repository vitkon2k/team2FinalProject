package com.web.finalproject.model;

import lombok.Data;

@Data
public class TokenResponse {
    private String token;
    private String type = "Bearer";
    private long expired;
}

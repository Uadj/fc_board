package com.example.fastcampusboard.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String userId;
    private String password;
}

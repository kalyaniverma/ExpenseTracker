package com.example.backend.Model;

import lombok.Data;

@Data
public class VerifyUserRequest {
    private String email;
    private String password;
}

package com.example.backend.Model;

import lombok.Data;

@Data
public class UserVerificationRequest {
    private String email;
    private String password;
}

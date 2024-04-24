package com.example.backend.Model;

import lombok.Data;

@Data
public class UserCreationRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
}

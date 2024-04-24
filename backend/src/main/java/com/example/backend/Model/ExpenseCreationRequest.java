package com.example.backend.Model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpenseCreationRequest {
    private String category;
    private LocalDate date;
    private int amount;
    private String description;
}

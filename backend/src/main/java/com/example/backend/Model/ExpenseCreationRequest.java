package com.example.backend.Model;

import com.example.backend.Entity.User;
import lombok.Data;
import java.util.Date;

@Data
public class ExpenseCreationRequest {
    private String category;
    private Date date;
    private int amount;
    private String description;
}

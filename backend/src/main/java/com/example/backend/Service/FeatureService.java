package com.example.backend.Service;

import com.example.backend.Entity.Expense;
import com.example.backend.Model.ExpenseCreationRequest;
import com.example.backend.Repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FeatureService {

    private final ExpenseRepository expenseRepository;

    public FeatureService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public boolean addExpense(ExpenseCreationRequest request){
        try{

            // Creating Expense Entity object
            Expense expense = new Expense();

            //Extracting expense information from request variable
            String category = request.getCategory();
            Date date = request.getDate();
            int amount = request.getAmount();
            String description = request.getDescription();

            //Storing the extracted information in the Expense table via expense object created
            expense.setCategory(category);
            expense.setDate(date);
            expense.setAmount(amount);
            expense.setDescription(description);

            // Save the user to the database
            expenseRepository.save(expense);
            return true;
        }

        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}

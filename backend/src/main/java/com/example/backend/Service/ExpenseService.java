package com.example.backend.Service;

import com.example.backend.Entity.Expense;
import com.example.backend.Entity.User;
import com.example.backend.Model.ExpenseCreationRequest;
import com.example.backend.Repository.ExpenseRepository;
import com.example.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    private final UserRepository userRepository;

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    public boolean addExpense(ExpenseCreationRequest request, int currentUserId){
        try{

            // Retrieve the user from the database using the provided userId
            Optional<User> optionalUser = userRepository.findById(currentUserId);

            // Creating Expense Entity object
            Expense expense = new Expense();

            // Extracting expense information from request variable
            String category = request.getCategory();
            Date date = request.getDate();
            int amount = request.getAmount();
            String description = request.getDescription();


            // Storing the extracted information in the Expense table via expense object created
            expense.setCategory(category);
            expense.setDate(date);
            expense.setAmount(amount);
            expense.setDescription(description);
            if(optionalUser.isPresent()){
                User user = optionalUser.get();
                expense.setUser(user);
            }


            // Save the expense to the database
            expenseRepository.save(expense);
            return true;
        }

        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List<Expense> getAllExpenses(int userId) {
        return expenseRepository.findByUserId(userId);
    }
}

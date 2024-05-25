package com.example.backend.Service;

import com.example.backend.Controller.ExpenseController;
import com.example.backend.Entity.Expense;
import com.example.backend.Entity.User;
import com.example.backend.Model.ExpenseCreationRequest;
import com.example.backend.Repository.ExpenseRepository;
import com.example.backend.Repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    private static final Logger logger = LogManager.getLogger(ExpenseController.class);

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
            LocalDate date = request.getDate();
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
            logger.info("Added Expense successfully :)");
            return true;
        }

        catch (Exception e){
            e.printStackTrace();
            logger.error("Couldn't add expense !!");
            return false;
        }
    }

    public boolean editExpense(int expenseId, ExpenseCreationRequest request) {

        // Fetch the expense from the repository
        Optional<Expense> optionalExpense = expenseRepository.findById(expenseId);
        if(optionalExpense.isPresent()){
            Expense expense = optionalExpense.get();

            // Update the expense with the new details
            expense.setCategory(request.getCategory());
            expense.setDate(request.getDate());
            expense.setAmount(request.getAmount());
            expense.setDescription(request.getDescription());

            // Save the updated expense
            expenseRepository.save(expense);
        }
        else{
            logger.warn("Expense to be edited not found !!");
            return false;
        }

        logger.info("Edited Expense successfully :)");
        return true;
    }

    public List<Expense> getAllExpenses(int userId) {
        logger.info("Displayed whole Expense list successfully");
        return expenseRepository.findByUserId(userId);
    }

    // Method to get all expenses sorted by latest date
    public List<Expense> getAllExpensesSortedByLatestDate(int userId) {
        logger.info("Displayed expenses sorted by Latest ");
        return expenseRepository.findByUserIdOrderByDateDesc(userId);
    }

    // Method to get all expenses sorted by highest amount
    public List<Expense> getAllExpensesSortedByHighestAmount(int userId) {
        logger.info("Displayed expenses sorted by Highest to lowest amount");
        return expenseRepository.findByUserIdOrderByAmountDesc(userId);
    }

    // Method to get all expenses sorted by lowest amount
    public List<Expense> getAllExpensesSortedByLowestAmount(int userId) {
        logger.info("Displayed expenses Sorted by Lowest to highest amount");
        return expenseRepository.findByUserIdOrderByAmountAsc(userId);
    }

    // Method to get all expenses filtered by category
    public List<Expense> filterExpensesByCategory(String category, int userId) {
        logger.info("Displayed Expenses Filtered by Category");
        return expenseRepository.findByUserIdAndCategory(userId, category);
    }

    // Method to get all expenses filtered by month
    public List<Expense> filterExpensesByMonth(int userId, LocalDate month) {
        logger.info("Displayed Expenses Month-wise");
        // Assuming you have a method in ExpenseRepository to filter expenses by user ID and month
        return expenseRepository.findByUserIdAndDate(userId, month);
    }

    // Method to search expense by a particular date
    public List<Expense> searchExpensesByDate(LocalDate date, int userId) {
        logger.info("Searched Expense by date {}", date);
        return expenseRepository.findByUserIdAndDate(userId, date);
    }

    public Boolean deleteExpense(int id) {
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if(optionalExpense.isPresent()){
            Expense expense = optionalExpense.get();
            expenseRepository.delete(expense);
            logger.info("Deleted Expense Successfully :)");
            return true;
        }
        else{
            logger.error("Couldn't delete expense !!");
            return false;
        }
    }

    @Transactional
    public void cleanup() {
        // Delete the expense with the stored ID
        Optional<Expense> expenseOptional = expenseRepository.findExpenseByUserIdIsNull();
        expenseOptional.ifPresent(expenseRepository::delete);
    }
}

package com.example.backend.Controller;

import com.example.backend.Configuration.UserSessionManager;
import com.example.backend.Entity.Expense;
import com.example.backend.Model.ExpenseCreationRequest;
import com.example.backend.Service.ExpenseService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "*")
public class ExpenseController {

    private final UserSessionManager userSessionManager;
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService, UserSessionManager userSessionManager) {
        this.expenseService = expenseService;
        this.userSessionManager = userSessionManager;
    }

    @PostMapping("/addExpense")
    public Boolean addExpense(@RequestBody ExpenseCreationRequest request){
        int currentUserId = userSessionManager.getCurrentUserId();
        return expenseService.addExpense(request, currentUserId);
    }

    @PutMapping("/editExpense/{expenseId}")
    public boolean editExpense(
            @PathVariable int expenseId,
            @RequestBody ExpenseCreationRequest request) {
        return expenseService.editExpense(expenseId, request);
    }

    @GetMapping("/listExpenses")
    public List<Expense> getAllExpenses(){
        int currentUser = userSessionManager.getCurrentUserId();
        return expenseService.getAllExpenses(currentUser);
    }

    // API to list all expenses sorted by latest date
    @GetMapping("/latest")
    public List<Expense> getAllExpensesSortedByLatestDate() {
        int currentUser = userSessionManager.getCurrentUserId();
        return expenseService.getAllExpensesSortedByLatestDate(currentUser);
    }

    // API to list all expenses sorted by highest amount
    @GetMapping("/highest-amount")
    public List<Expense> getAllExpensesSortedByHighestAmount() {
        int currentUser = userSessionManager.getCurrentUserId();
        return expenseService.getAllExpensesSortedByHighestAmount(currentUser);
    }

    // API to list all expenses sorted by lowest amount
    @GetMapping("/lowest-amount")
    public List<Expense> getAllExpensesSortedByLowestAmount() {
        int currentUser = userSessionManager.getCurrentUserId();
        return expenseService.getAllExpensesSortedByLowestAmount(currentUser);
    }

    // API to list all expenses filtered by category
    @GetMapping("/category/{category}")
    public List<Expense> getExpensesByCategory(@PathVariable String category) {
        int currentUser = userSessionManager.getCurrentUserId();
        return expenseService.getExpensesByCategory(category, currentUser);
    }

    @GetMapping("/searchByDate")
    public List<Expense> searchExpensesByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        int currentUser = userSessionManager.getCurrentUserId();
        return expenseService.searchExpensesByDate(date, currentUser);
    }
}

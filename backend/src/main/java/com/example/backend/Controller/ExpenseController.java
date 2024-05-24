package com.example.backend.Controller;

import com.example.backend.Configuration.UserSessionManager;
import com.example.backend.Entity.Expense;
import com.example.backend.Model.ExpenseCreationRequest;
import com.example.backend.Service.ExpenseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "*")
public class ExpenseController {
    private static final Logger logger = LogManager.getLogger(ExpenseController.class);

    private final UserSessionManager userSessionManager;
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService, UserSessionManager userSessionManager) {
        this.expenseService = expenseService;
        this.userSessionManager = userSessionManager;
    }

    @PostMapping("/addExpense")
    public Boolean addExpense(@RequestBody ExpenseCreationRequest request){
        logger.info("API: Add Expense");
        int currentUserId = userSessionManager.getCurrentUserId();
        return expenseService.addExpense(request, currentUserId);
    }

    @PutMapping("/editExpense/{expenseId}")
    public boolean editExpense(
            @PathVariable int expenseId,
            @RequestBody ExpenseCreationRequest request) {
        logger.info("API: Edit Expense");
        return expenseService.editExpense(expenseId, request);
    }

    @GetMapping("/listExpenses")
    public List<Expense> getAllExpenses(){
        logger.info("API: Display List of Expenses");
        int currentUser = userSessionManager.getCurrentUserId();
        return expenseService.getAllExpenses(currentUser);
    }

    // API to list all expenses sorted by latest date
    @GetMapping("/latest")
    public List<Expense> getAllExpensesSortedByLatestDate() {
        logger.info("API: View expenses by Latest");
        int currentUser = userSessionManager.getCurrentUserId();
        return expenseService.getAllExpensesSortedByLatestDate(currentUser);
    }

    // API to list all expenses sorted by highest amount
    @GetMapping("/highest-amount")
    public List<Expense> getAllExpensesSortedByHighestAmount() {
        logger.info("API: View Expenses ranging from higher to lower amount");
        int currentUser = userSessionManager.getCurrentUserId();
        return expenseService.getAllExpensesSortedByHighestAmount(currentUser);
    }

    // API to list all expenses sorted by lowest amount
    @GetMapping("/lowest-amount")
    public List<Expense> getAllExpensesSortedByLowestAmount() {
        logger.info("API: View Expenses ranging from lower to higher amount");
        int currentUser = userSessionManager.getCurrentUserId();
        return expenseService.getAllExpensesSortedByLowestAmount(currentUser);
    }

    // API to list all expenses filtered by category
    @GetMapping("/expenses/{category}")
    public List<Expense> filterExpensesByCategory(@PathVariable String category) {
        logger.info("API: Filter Expenses by category");
        int currentUser = userSessionManager.getCurrentUserId();
        return expenseService.filterExpensesByCategory(category, currentUser);
    }

    @GetMapping("/expenses/filterByMonth")
    public List<Expense> filterExpensesByMonth(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM") LocalDate month) {
        logger.info("API: Filter Expenses month-wise");
        int currentUser = userSessionManager.getCurrentUserId();
        return expenseService.filterExpensesByMonth(currentUser, month);
    }

    @GetMapping("/searchByDate")
    public List<Expense> searchExpensesByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        logger.info("API: Search Expenses by date");
        int currentUser = userSessionManager.getCurrentUserId();
        return expenseService.searchExpensesByDate(date, currentUser);
    }

    @DeleteMapping("/deleteExpense")
    public Boolean deleteExpense(@RequestParam int id){
        logger.info("API: Delete Expense");
        return expenseService.deleteExpense(id);
    }

}

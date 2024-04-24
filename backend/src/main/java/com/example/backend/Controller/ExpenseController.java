package com.example.backend.Controller;

import com.example.backend.Configuration.UserSessionManager;
import com.example.backend.Entity.Expense;
import com.example.backend.Model.ExpenseCreationRequest;
import com.example.backend.Service.ExpenseService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/listExpenses")
    public List<Expense> getAllExpenses(){
        int currentUser = userSessionManager.getCurrentUserId();
        return expenseService.getAllExpenses(currentUser);
    }
}

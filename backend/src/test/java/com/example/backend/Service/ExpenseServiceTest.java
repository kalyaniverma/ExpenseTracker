package com.example.backend.Service;

import com.example.backend.Entity.Expense;
import com.example.backend.Entity.User;
import com.example.backend.Model.ExpenseCreationRequest;
import com.example.backend.Repository.ExpenseRepository;
import com.example.backend.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ExpenseServiceTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ExpenseService expenseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddExpense_Success() {
        ExpenseCreationRequest request = new ExpenseCreationRequest();
        request.setCategory("Grocery");
        request.setDate(LocalDate.now());
        request.setAmount(50);
        request.setDescription("Grocery shopping");

        when(userRepository.findById(anyInt())).thenReturn(Optional.of(new User())); // assuming User class exists
        when(expenseRepository.save(any(Expense.class))).thenReturn(new Expense());

        boolean result = expenseService.addExpense(request, 1);

        assertTrue(result);
        verify(expenseRepository, times(1)).save(any(Expense.class));
    }

    @Test
    public void testEditExpense_Success() {
        ExpenseCreationRequest request = new ExpenseCreationRequest();
        request.setCategory("Grocery");
        request.setDate(LocalDate.now());
        request.setAmount(50);
        request.setDescription("Grocery shopping");

        when(expenseRepository.findById(anyInt())).thenReturn(Optional.of(new Expense()));
        when(expenseRepository.save(any(Expense.class))).thenReturn(new Expense());

        boolean result = expenseService.editExpense(1, request);

        assertTrue(result);
        verify(expenseRepository, times(1)).save(any(Expense.class));
    }

    @Test
    public void testGetAllExpenses() {
        List<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense());
        expenses.add(new Expense());

        when(expenseRepository.findByUserId(anyInt())).thenReturn(expenses);

        List<Expense> result = expenseService.getAllExpenses(1);

        assertFalse(result.isEmpty());
        assertTrue(result.size() == 2);
    }

    // Other test cases for getAllExpensesSortedByLatestDate, getAllExpensesSortedByHighestAmount, etc.
}

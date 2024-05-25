package com.example.backend.Controller;

import com.example.backend.Configuration.UserSessionManager;
import com.example.backend.Model.ExpenseCreationRequest;
import com.example.backend.Service.ExpenseService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExpenseController.class)
public class ExpenseControllerTest {
    @MockBean
    private ExpenseService expenseService;

    @MockBean
    private UserSessionManager userSessionManager;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddExpense() throws Exception {
        ExpenseCreationRequest request = new ExpenseCreationRequest();
        request.setCategory("Grocery");
        request.setDate(LocalDate.now());
        request.setAmount(50);
        request.setDescription("Grocery shopping");

        when(expenseService.addExpense(any(ExpenseCreationRequest.class), anyInt())).thenReturn(true);

        mockMvc.perform(post("/addExpense")
                        .contentType("application/json")
                        .content("{\"category\":\"Grocery\",\"date\":\"2024-05-28\",\"amount\":50,\"description\":\"Grocery shopping\"}"))
                .andExpect(status().isOk());

        verify(expenseService, times(1)).addExpense(any(ExpenseCreationRequest.class), anyInt());
    }
}

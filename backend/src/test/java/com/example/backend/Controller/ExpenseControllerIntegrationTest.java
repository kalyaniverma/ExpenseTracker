package com.example.backend.Controller;

import com.example.backend.Configuration.UserSessionManager;
import com.example.backend.Model.ExpenseCreationRequest;
import com.example.backend.Service.ExpenseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExpenseControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private UserSessionManager userSessionManager;

    private int expenseId;

    @Test
    public void testAddExpense() throws Exception {
        // Create a sample request
        ExpenseCreationRequest request = new ExpenseCreationRequest();
        request.setCategory("Grocery");
        request.setDate(LocalDate.now());
        request.setAmount(50);
        request.setDescription("Grocery shopping");

        // Perform the request
        ResultActions resultActions = mockMvc.perform(post("/addExpense")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"category\":\"Grocery\",\"date\":\"2024-05-28\",\"amount\":50,\"description\":\"Grocery shopping\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        expenseService.cleanup();
        // You can also add more assertions here to verify the behavior
    }
}

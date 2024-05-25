package com.example.backend.Controller;

import com.example.backend.Model.UserCreationRequest;
import com.example.backend.Model.UserVerificationRequest;
import com.example.backend.Service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @Test
    public void testCreateUser() throws Exception {
        UserCreationRequest request = new UserCreationRequest();
        request.setFirstname("John");
        request.setLastname("Doe");
        request.setEmail("john.doe@example.com");
        request.setPassword("password");

        when(authService.createUser(any(UserCreationRequest.class))).thenReturn(true);

        String requestBody = "{ \"firstname\": \"John\", \"lastname\": \"Doe\", \"email\": \"john.doe@example.com\", \"password\": \"password\" }";

        mockMvc.perform(post("/signup")
                        .contentType(APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(authService, times(1)).createUser(any(UserCreationRequest.class));
    }

    @Test
    public void testVerifyUser() throws Exception {
        UserVerificationRequest request = new UserVerificationRequest();
        request.setEmail("test@example.com");
        request.setPassword("password");

        when(authService.verifyUser(any(UserVerificationRequest.class))).thenReturn("success");

        mockMvc.perform(post("/login")
                        .contentType("application/json")
                        .content("{\"email\":\"test@example.com\",\"password\":\"password\"}"))
                        .andExpect(status().isOk())
                        .andReturn();

        verify(authService, times(1)).verifyUser(any(UserVerificationRequest.class));
    }
}

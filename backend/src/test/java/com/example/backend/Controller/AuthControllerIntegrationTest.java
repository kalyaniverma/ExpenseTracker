package com.example.backend.Controller;

import com.example.backend.Model.UserCreationRequest;
import com.example.backend.Repository.UserRepository;
import com.example.backend.Service.AuthService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Add this annotation
public class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateUser() throws Exception {
        String email = "john.doe@example.com";
        // Create a sample request
        UserCreationRequest request = new UserCreationRequest();
        request.setFirstname("John");
        request.setLastname("Doe");
        request.setEmail(email);
        request.setPassword("password");

        // Perform the request
        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"firstname\": \"John\", \"lastname\": \"Doe\", \"email\": \"john.doe@example.com\", \"password\": \"password\" }"))
                        .andExpect(status().isOk())
                        .andExpect(content().string("true"));

        authService.cleanup(email);
    }

    @Test
    public void testVerifyUser() throws Exception {
        // Prepare the request body
        String requestBody = "{\"email\":\"john.doe@example.com\",\"password\":\"password\"}";

        // Perform the POST request to the login API
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                        .andExpect(status().isOk())
                        .andReturn();

        // Assert the response content
        String content = result.getResponse().getContentAsString();
        // Modify the assertions based on your expected responses
        // For example, if "Logged In successfully" is expected:
        assert(content.equals("User does not exist !!"));
    }

}

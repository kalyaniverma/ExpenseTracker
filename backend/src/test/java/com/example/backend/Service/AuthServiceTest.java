package com.example.backend.Service;

import com.example.backend.Configuration.UserSessionManager;
import com.example.backend.Entity.User;
import com.example.backend.Model.UserCreationRequest;
import com.example.backend.Model.UserVerificationRequest;
import com.example.backend.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserSessionManager userSessionManager;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser_Success() {
        UserCreationRequest request = new UserCreationRequest();
        request.setFirstname("John");
        request.setLastname("Doe");
        request.setEmail("john.doe@example.com");
        request.setPassword("password");

        when(userRepository.save(any(User.class))).thenReturn(new User());

        boolean result = authService.createUser(request);

        assertTrue(result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testCreateUser_Failure() {
        UserCreationRequest request = new UserCreationRequest();
        request.setFirstname("John");
        request.setLastname("Doe");
        request.setEmail("john.doe@example.com");
        request.setPassword("password");

        doThrow(new RuntimeException()).when(userRepository).save(any(User.class));

        boolean result = authService.createUser(request);

        assertFalse(result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testVerifyUser_Success() {
        UserVerificationRequest request = new UserVerificationRequest();
        request.setEmail("john.doe@example.com");
        request.setPassword("password");

        User user = new User();
        user.setId(1);
        user.setEmail("john.doe@example.com");
        user.setPassword("password");

        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.of(user));

        String result = authService.verifyUser(request);

        assertEquals("Logged In successfully", result);
        verify(userSessionManager, times(1)).setCurrentUserId(user.getId());
    }

    @Test
    public void testVerifyUser_InvalidCredentials() {
        UserVerificationRequest request = new UserVerificationRequest();
        request.setEmail("john.doe@example.com");
        request.setPassword("wrongpassword");

        User user = new User();
        user.setEmail("john.doe@example.com");
        user.setPassword("password");

        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.of(user));

        String result = authService.verifyUser(request);

        assertEquals("Invalid Credentials !!", result);
        verify(userSessionManager, times(0)).setCurrentUserId(anyInt());
    }

    @Test
    public void testVerifyUser_UserNotFound() {
        UserVerificationRequest request = new UserVerificationRequest();
        request.setEmail("john.doe@example.com");
        request.setPassword("password");

        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.empty());

        String result = authService.verifyUser(request);

        assertEquals("User does not exist !!", result);
        verify(userSessionManager, times(0)).setCurrentUserId(anyInt());
    }
}

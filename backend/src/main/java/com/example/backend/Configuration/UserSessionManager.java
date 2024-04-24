package com.example.backend.Configuration;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class UserSessionManager {
    private int currentUserId;

    public void setCurrentUserId(int currentUserId) {
        this.currentUserId = currentUserId;
    }
}

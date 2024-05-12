package com.example.backend.Controller;


import com.example.backend.Model.UserCreationRequest;
import com.example.backend.Model.UserVerificationRequest;
import com.example.backend.Service.AuthService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("")
@CrossOrigin(origins = "*")
public class AuthController {
    private static final Logger logger = LogManager.getLogger(AuthController.class);

    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public boolean createUser(@RequestBody UserCreationRequest request){
        logger.info("API: SignUp");
        //Creating User
        return authService.createUser(request);

    }

    @PostMapping("/login")
    public String verifyUser(@RequestBody UserVerificationRequest request){
        logger.info("API: Login");
        return authService.verifyUser(request);
    }

}

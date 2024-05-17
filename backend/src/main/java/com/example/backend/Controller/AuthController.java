package com.example.backend.Controller;


import com.example.backend.Model.UserCreationRequest;
import com.example.backend.Model.UserVerificationRequest;
import com.example.backend.Service.AuthService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public boolean createUser(@RequestBody UserCreationRequest request){
        //Creating User
        return authService.createUser(request);

    }

    @PostMapping("/login")
    public Boolean verifyUser(@RequestBody UserVerificationRequest request){
        return authService.verifyUser(request);
    }

//    @PostMapping("/login2")
//    public Boolean verifyUser2(@RequestBody UserVerificationRequest request){
//        return authService.verifyUser2(request);
//    }

}

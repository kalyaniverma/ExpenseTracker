package com.example.backend.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "*")
public class FeaturesContoller {

    @PostMapping("/addExpense")
    public Boolean addExpense(@RequestBody )
}

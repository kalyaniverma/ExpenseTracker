package com.example.backend.Controller;

import com.example.backend.Model.ExpenseCreationRequest;
import com.example.backend.Service.FeatureService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "*")
public class FeatureContoller {

    private final FeatureService featureService;

    public FeatureContoller(FeatureService featureService) {
        this.featureService = featureService;
    }

    @PostMapping("/addExpense")
    public Boolean addExpense(@RequestBody ExpenseCreationRequest request){
        return featureService.addExpense(request);
    }
}

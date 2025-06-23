package com.example.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationViewController {
    
    @GetMapping("/register")
    public String showRegistrationForm(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", error);
        }
        return "register";
    }
}

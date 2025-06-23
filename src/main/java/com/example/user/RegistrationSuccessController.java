package com.example.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationSuccessController {
    
    private final UserRepository userRepository;
    
    @Autowired
    public RegistrationSuccessController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @GetMapping("/registration-success")
    public String showSuccessPage(@RequestParam Long id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        // Don't send the password to the view
        user.setPassword("[PROTECTED]");
        model.addAttribute("user", user);
        
        return "registration-success";
    }
}

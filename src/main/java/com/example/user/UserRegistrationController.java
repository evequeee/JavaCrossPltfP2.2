package com.example.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api/users")
public class UserRegistrationController {
    
    private final UserService userService;
    
    @Autowired
    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/register")
    public RedirectView registerUser(UserRegistrationDto registrationDto) {
        try {
            User user = userService.registerNewUser(registrationDto);
            // Redirect to success page or login page
            return new RedirectView("/registration-success?id=" + user.getId());
        } catch (Exception e) {
            // Redirect back to registration form with error
            return new RedirectView("/register?error=" + e.getMessage());
        }
    }
    
    // This endpoint can be used to verify user creation (for testing)
    @GetMapping("/verification/{id}")
    public ResponseEntity<User> verifyUser(@PathVariable Long id) {
        return userService.findUserById(id)
            .map(user -> {
                // Hide password in response
                user.setPassword("[PROTECTED]");
                return new ResponseEntity<>(user, HttpStatus.OK);
            })
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

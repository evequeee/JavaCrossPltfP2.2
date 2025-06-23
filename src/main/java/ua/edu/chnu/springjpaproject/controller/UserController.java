package ua.edu.chnu.springjpaproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.edu.chnu.springjpaproject.service.UserService;
import ua.edu.chnu.springjpaproject.user.User;
import ua.edu.chnu.springjpaproject.user.UserRole;

@Controller
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/register")
    public String registerUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("role") String role,
            Model model) {

        try {
            User user = new User(username, passwordEncoder.encode(password));
            user.setRole(UserRole.valueOf(role));
            userService.saveUser(user);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Помилка при реєстрації: " + e.getMessage());
            addCurrentUserToModel(model);
            return "register";
        }
    }
}
package ua.edu.chnu.springjpaproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.edu.chnu.springjpaproject.user.dto.UserRegistrationDto;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController extends BaseController {

    /**
     * Головна сторінка
     */
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Головна сторінка");
        addCurrentUserToModel(model);
        return "index";  // повертає templates/index.jte
    }

    /**
     * Сторінка реєстрації
     */
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new ua.edu.chnu.springjpaproject.user.dto.UserRegistrationDto());
        model.addAttribute("errors", new java.util.HashMap<String, String>());
        addCurrentUserToModel(model);
        return "register";
    }

    /**
     * Сторінка входу
     */
    @GetMapping("/login")
    public String login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model) {

        if (error != null) {
            model.addAttribute("error", "true");
        }

        if (logout != null) {
            model.addAttribute("logout", "true");
        }

        addCurrentUserToModel(model);
        return "login";
    }
}
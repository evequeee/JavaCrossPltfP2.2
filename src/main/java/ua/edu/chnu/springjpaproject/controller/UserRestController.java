package ua.edu.chnu.springjpaproject.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.chnu.springjpaproject.user.User;
import ua.edu.chnu.springjpaproject.service.UserService;
import ua.edu.chnu.springjpaproject.user.dto.UserRegistrationDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController  // Змінити на @RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    /**
     * REST API для реєстрації користувача
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationDto registrationDto) {
        try {
            // Перевірка унікальності username
            if (userService.existsByUsername(registrationDto.getUsername())) {
                Map<String, String> errors = new HashMap<>();
                errors.put("username", "Користувач з таким ім'ям вже існує");
                return ResponseEntity.badRequest().body(errors);
            }

            // Перевірка співпадіння паролів
            if (!registrationDto.isPasswordsMatch()) {
                Map<String, String> errors = new HashMap<>();
                errors.put("confirmPassword", "Паролі не співпадають");
                return ResponseEntity.badRequest().body(errors);
            }

            // Реєстрація користувача
            User savedUser = userService.registerUser(registrationDto);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Користувач успішно зареєстрований");
            response.put("userId", savedUser.getId());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, String> errors = new HashMap<>();
            errors.put("general", "Помилка реєстрації: " + e.getMessage());
            return ResponseEntity.badRequest().body(errors);
        }
    }

    /**
     * REST API для отримання всіх користувачів
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * REST API для перевірки доступності username
     */
    @GetMapping("/check-username")
    public ResponseEntity<Map<String, Boolean>> checkUsername(@RequestParam String username) {
        boolean exists = userService.existsByUsername(username);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        response.put("available", !exists);
        return ResponseEntity.ok(response);
    }

    /**
     * REST API для отримання статистики користувачів
     */
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getUserStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalUsers", userService.getUserCount());
        stats.put("adminUsers", userService.getUserCountByRole(ua.edu.chnu.springjpaproject.user.UserRole.ADMIN));
        stats.put("regularUsers", userService.getUserCountByRole(ua.edu.chnu.springjpaproject.user.UserRole.USER));
        return ResponseEntity.ok(stats);
    }
}
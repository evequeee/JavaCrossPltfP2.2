package ua.edu.chnu.springjpaproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.edu.chnu.springjpaproject.repository.UserRepository;
import ua.edu.chnu.springjpaproject.user.User;
import ua.edu.chnu.springjpaproject.user.UserRole;
import ua.edu.chnu.springjpaproject.user.dto.UserRegistrationDto;

import java.util.List;
import java.util.logging.Logger;

@Service
public class UserService {

    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        logger.info("Початок збереження користувача: " + user.getUsername());

        // Check if username already exists
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            logger.warning("Користувач з ім'ям " + user.getUsername() + " вже існує");
            throw new RuntimeException("Користувач з таким ім'ям вже існує");
        }

        try {
            User savedUser = userRepository.save(user);
            logger.info("Користувач " + user.getUsername() + " успішно збережений з ID: " + savedUser.getId());
            return savedUser;
        } catch (Exception e) {
            logger.severe("Помилка при збереженні користувача " + user.getUsername() + ": " + e.getMessage());
            throw e;
        }
    }

    /**
     * Перевіряє чи існує користувач з вказаним ім'ям
     */
    public boolean existsByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    /**
     * Реєструє нового користувача з даних DTO
     */
    public User registerUser(UserRegistrationDto registrationDto) {
        User user = new User(
                registrationDto.getUsername(),
                passwordEncoder.encode(registrationDto.getPassword())
        );
        user.setRole(UserRole.USER); // За замовчуванням встановлюємо роль USER
        return saveUser(user);
    }

    /**
     * Повертає список всіх користувачів
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Повертає загальну кількість користувачів
     */
    public long getUserCount() {
        return userRepository.count();
    }

    /**
     * Повертає кількість користувачів з вказаною роллю
     */
    public long getUserCountByRole(UserRole role) {
        return userRepository.findAll().stream()
                .filter(user -> user.getRole() == role)
                .count();
    }
}
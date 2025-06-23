package ua.edu.chnu.springjpaproject.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRegistrationDto {

    @NotBlank(message = "Ім'я користувача не може бути порожнім")
    @Size(min = 3, max = 255, message = "Ім'я користувача повинно містити від 3 до 255 символів")
    private String username;

    @NotBlank(message = "Пароль не може бути порожнім")
    @Size(min = 6, message = "Пароль повинен містити мінімум 6 символів")
    private String password;

    @NotBlank(message = "Підтвердження паролю не може бути порожнім")
    private String confirmPassword;

    // Конструктори
    public UserRegistrationDto() {}

    public UserRegistrationDto(String username, String password, String confirmPassword) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     * Перевірка чи співпадають паролі
     */
    public boolean isPasswordsMatch() {
        return password != null && password.equals(confirmPassword);
    }

    @Override
    public String toString() {
        return "UserRegistrationDto{" +
                "username='" + username + '\'' +
                ", password='***'" +
                ", confirmPassword='***'" +
                '}';
    }
}
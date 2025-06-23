package ua.edu.chnu.springjpaproject.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

/**
 * Базовий контролер, який містить спільні методи для всіх контролерів
 */
public abstract class BaseController {

    /**
     * Додає інформацію про поточного користувача в модель
     * @param model Модель для передачі даних у шаблон
     */
    protected void addCurrentUserToModel(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()
                && !authentication.getName().equals("anonymousUser")) {
            model.addAttribute("currentUser", authentication.getName());
        }
    }
}

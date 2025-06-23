package ua.edu.chnu.springjpaproject.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Обробник успішної автентифікації, який перенаправляє користувача на потрібну сторінку
 */
@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private static final Logger logger = Logger.getLogger(CustomAuthenticationSuccessHandler.class.getName());

    public CustomAuthenticationSuccessHandler() {
        // Встановлюємо URL за замовчуванням для перенаправлення після успішної автентифікації
        setDefaultTargetUrl("/");
        // Завжди перенаправляти на defaultTargetUrl, якщо немає збереженого запиту
        setAlwaysUseDefaultTargetUrl(false);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        logger.info("Користувач " + authentication.getName() + " успішно автентифікований");

        // Встановлюємо повідомлення в сесію, яке можна буде вивести на сторінці після перенаправлення
        request.getSession().setAttribute("welcomeMessage", "Вітаємо, " + authentication.getName() + "!");

        // Викликаємо батьківський метод для обробки перенаправлення
        super.onAuthenticationSuccess(request, response, authentication);
    }
}

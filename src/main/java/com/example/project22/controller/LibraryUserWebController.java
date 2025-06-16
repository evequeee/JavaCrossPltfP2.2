package com.example.project22.controller;

import com.example.project22.model.LibraryUser;
import com.example.project22.service.LibraryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class LibraryUserWebController {

    private final LibraryUserService userService;

    @Autowired
    public LibraryUserWebController(LibraryUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String listUsers(Model model) {
        List<LibraryUser> users = userService.findAll();
        model.addAttribute("users", users);
        return "users/list";
    }

    @GetMapping("/search")
    public String searchUsers(@RequestParam(required = false) String name,
                             @RequestParam(required = false) String email,
                             @RequestParam(required = false) String status,
                             Model model) {

        if (email != null && !email.isEmpty()) {
            Optional<LibraryUser> userOptional = userService.findByEmail(email);
            if (userOptional.isPresent()) {
                model.addAttribute("users", List.of(userOptional.get()));
            } else {
                model.addAttribute("users", List.of());
            }
        } else if (name != null && !name.isEmpty()) {
            // Припускаємо, що ім'я може бути або ім'ям, або прізвищем
            List<LibraryUser> users = userService.findByLastName(name);
            model.addAttribute("users", users);
        } else if (status != null && !status.isEmpty()) {
            boolean isActive = "active".equalsIgnoreCase(status);
            List<LibraryUser> users = userService.findByActiveStatus(isActive);
            model.addAttribute("users", users);
        } else {
            List<LibraryUser> users = userService.findAll();
            model.addAttribute("users", users);
        }

        return "users/list";
    }

    @GetMapping("/{id}")
    public String viewUser(@PathVariable Long id, Model model) {
        Optional<LibraryUser> user = userService.findById(id);

        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "users/view";
        } else {
            return "redirect:/users";
        }
    }

    @GetMapping("/new")
    public String createUserForm(Model model) {
        model.addAttribute("user", new LibraryUser());
        return "users/form";
    }

    @GetMapping("/{id}/edit")
    public String editUserForm(@PathVariable Long id, Model model) {
        Optional<LibraryUser> user = userService.findById(id);

        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "users/form";
        } else {
            return "redirect:/users";
        }
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute LibraryUser user, RedirectAttributes redirectAttributes) {
        // Перевірка на існування користувача з таким email
        if (user.getId() == null && userService.existsByEmail(user.getEmail())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Користувач з таким email вже існує");
            return "redirect:/users/new";
        }

        LibraryUser savedUser = userService.save(user);
        redirectAttributes.addFlashAttribute("successMessage", "Користувач успішно збережений");
        return "redirect:/users/" + savedUser.getId();
    }

    @GetMapping("/{id}/activate")
    public String activateUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        boolean activated = userService.activateUser(id);
        if (activated) {
            redirectAttributes.addFlashAttribute("successMessage", "Користувач успішно активований");
        }
        return "redirect:/users/" + id;
    }

    @GetMapping("/{id}/deactivate")
    public String deactivateUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        boolean deactivated = userService.deactivateUser(id);
        if (deactivated) {
            redirectAttributes.addFlashAttribute("successMessage", "Користувач успішно деактивований");
        }
        return "redirect:/users/" + id;
    }

    @GetMapping("/{id}/extend-membership")
    public String extendMembershipForm(@PathVariable Long id, Model model) {
        Optional<LibraryUser> user = userService.findById(id);

        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            model.addAttribute("newExpiryDate",
                    user.get().getMembershipExpires() != null
                    ? user.get().getMembershipExpires().plusYears(1)
                    : LocalDate.now().plusYears(1));
            return "users/extend-membership";
        } else {
            return "redirect:/users";
        }
    }

    @PostMapping("/{id}/extend-membership")
    public String extendMembership(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate expiryDate,
            RedirectAttributes redirectAttributes) {
        boolean extended = userService.extendMembership(id, expiryDate);
        if (extended) {
            redirectAttributes.addFlashAttribute("successMessage", "Членство успішно продовжено");
        }
        return "redirect:/users/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (userService.exists(id)) {
            userService.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Користувач успішно видалений");
        }
        return "redirect:/users";
    }
}

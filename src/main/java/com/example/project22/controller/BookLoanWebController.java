package com.example.project22.controller;

import com.example.project22.model.Book;
import com.example.project22.model.BookLoan;
import com.example.project22.model.LibraryUser;
import com.example.project22.service.BookLoanService;
import com.example.project22.service.BookService;
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
@RequestMapping("/loans")
public class BookLoanWebController {

    private final BookLoanService loanService;
    private final BookService bookService;
    private final LibraryUserService userService;

    @Autowired
    public BookLoanWebController(BookLoanService loanService, BookService bookService, LibraryUserService userService) {
        this.loanService = loanService;
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping
    public String listLoans(@RequestParam(required = false) String status, Model model) {
        List<BookLoan> loans;

        if (status != null) {
            switch (status) {
                case "active":
                    loans = loanService.findByReturnStatus(false);
                    break;
                case "overdue":
                    loans = loanService.findOverdueLoans();
                    break;
                case "returned":
                    loans = loanService.findByReturnStatus(true);
                    break;
                default:
                    loans = loanService.findAll();
            }
        } else {
            loans = loanService.findAll();
        }

        model.addAttribute("loans", loans);
        return "loans/list";
    }

    @GetMapping("/search")
    public String searchLoans(@RequestParam(required = false) String user,
                             @RequestParam(required = false) String book,
                             Model model) {
        List<BookLoan> loans;

        if (user != null && !user.isEmpty()) {
            // Тут можна реалізувати пошук за користувачем, наприклад, по ID або імені
            // Для простоти поки що повертаємо всі позичення
            loans = loanService.findAll();
        } else if (book != null && !book.isEmpty()) {
            // Тут можна реалізувати пошук за книгою, наприклад, по ID або назві
            // Для простоти поки що повертаємо всі позичення
            loans = loanService.findAll();
        } else {
            loans = loanService.findAll();
        }

        model.addAttribute("loans", loans);
        return "loans/list";
    }

    @GetMapping("/{id}")
    public String viewLoan(@PathVariable Long id, Model model) {
        Optional<BookLoan> loan = loanService.findById(id);

        if (loan.isPresent()) {
            model.addAttribute("loan", loan.get());
            return "loans/view";
        } else {
            return "redirect:/loans";
        }
    }

    @GetMapping("/new")
    public String createLoanForm(@RequestParam(required = false) Long bookId, Model model) {
        // Отримуємо доступні книги
        List<Book> availableBooks = bookService.findAvailableBooks();

        // Отримуємо активних користувачів
        List<LibraryUser> activeUsers = userService.findByActiveStatus(true);

        model.addAttribute("availableBooks", availableBooks);
        model.addAttribute("activeUsers", activeUsers);
        model.addAttribute("selectedBookId", bookId);
        model.addAttribute("defaultDueDate", LocalDate.now().plusDays(14)); // Стандартний термін - 2 тижні

        return "loans/form";
    }

    @PostMapping("/save")
    public String saveLoan(@RequestParam Long bookId,
                          @RequestParam Long userId,
                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate,
                          RedirectAttributes redirectAttributes) {
        BookLoan loan = loanService.borrowBook(bookId, userId, dueDate);

        if (loan != null) {
            redirectAttributes.addFlashAttribute("successMessage", "Книгу успішно видано");
            return "redirect:/loans/" + loan.getId();
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Помилка при видачі книги. Перевірте доступність книги та статус користувача.");
            return "redirect:/loans/new";
        }
    }

    @GetMapping("/{id}/return")
    public String returnLoanForm(@PathVariable Long id, Model model) {
        Optional<BookLoan> loan = loanService.findById(id);

        if (loan.isPresent() && !loan.get().getIsReturned()) {
            model.addAttribute("loan", loan.get());
            model.addAttribute("returnDate", LocalDate.now());
            model.addAttribute("conditions", List.of("Відмінний", "Добрий", "Задовільний", "Поганий", "Пошкоджений"));
            return "loans/return";
        } else {
            return "redirect:/loans";
        }
    }

    @PostMapping("/{id}/return")
    public String returnLoan(@PathVariable Long id,
                            @RequestParam String condition,
                            RedirectAttributes redirectAttributes) {
        BookLoan returnedLoan = loanService.returnBook(id, condition);

        if (returnedLoan != null) {
            redirectAttributes.addFlashAttribute("successMessage", "Книгу успішно повернуто");
            return "redirect:/loans/" + id;
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Помилка при поверненні книги");
            return "redirect:/loans/" + id + "/return";
        }
    }

    @GetMapping("/{id}/extend")
    public String extendLoanForm(@PathVariable Long id, Model model) {
        Optional<BookLoan> loan = loanService.findById(id);

        if (loan.isPresent() && !loan.get().getIsReturned()) {
            model.addAttribute("loan", loan.get());
            // Пропонуємо продовжити термін на 2 тижні від поточної дати
            model.addAttribute("newDueDate", LocalDate.now().plusDays(14));
            return "loans/extend";
        } else {
            return "redirect:/loans";
        }
    }

    @PostMapping("/{id}/extend")
    public String extendLoan(@PathVariable Long id,
                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate newDueDate,
                            RedirectAttributes redirectAttributes) {
        BookLoan extendedLoan = loanService.extendLoan(id, newDueDate);

        if (extendedLoan != null) {
            redirectAttributes.addFlashAttribute("successMessage", "Термін позичення успішно продовжено");
            return "redirect:/loans/" + id;
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Помилка при продовженні терміну. Можливо, позичення вже повернуто або прострочено.");
            return "redirect:/loans/" + id + "/extend";
        }
    }

    @PostMapping("/{id}/delete")
    public String deleteLoan(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (loanService.exists(id)) {
            loanService.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Позичення успішно видалено");
        }
        return "redirect:/loans";
    }
}

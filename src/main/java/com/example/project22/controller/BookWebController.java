package com.example.project22.controller;

import com.example.project22.model.Book;
import com.example.project22.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookWebController {

    private final BookService bookService;

    @Autowired
    public BookWebController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String listBooks(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "books/list";
    }

    @GetMapping("/search")
    public String searchBooks(@RequestParam(required = false) String title,
                             @RequestParam(required = false) String author,
                             @RequestParam(required = false) String genre,
                             Model model) {
        List<Book> books;

        if (title != null && !title.isEmpty()) {
            books = bookService.findByTitle(title);
        } else if (author != null && !author.isEmpty()) {
            // Можна реалізувати пошук за автором, якщо потрібно
            books = bookService.findAll();
        } else if (genre != null && !genre.isEmpty()) {
            // Можна реалізувати пошук за жанром, якщо потрібно
            books = bookService.findAll();
        } else {
            books = bookService.findAll();
        }

        model.addAttribute("books", books);
        return "books/list";
    }

    @GetMapping("/{id}")
    public String viewBook(@PathVariable Long id, Model model) {
        Optional<Book> book = bookService.findById(id);

        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            return "books/view";
        } else {
            return "redirect:/books";
        }
    }
}

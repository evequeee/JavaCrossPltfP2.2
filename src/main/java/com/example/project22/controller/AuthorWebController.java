package com.example.project22.controller;

import com.example.project22.model.Author;
import com.example.project22.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/authors")
public class AuthorWebController {

    private final AuthorService authorService;

    @Autowired
    public AuthorWebController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String listAuthors(Model model) {
        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        return "authors/list";
    }

    @GetMapping("/search")
    public String searchAuthors(@RequestParam(required = false) String name,
                               @RequestParam(required = false) String nationality,
                               Model model) {
        List<Author> authors;

        if (name != null && !name.isEmpty()) {
            authors = authorService.findByLastName(name);
        } else if (nationality != null && !nationality.isEmpty()) {
            authors = authorService.findByNationality(nationality);
        } else {
            authors = authorService.findAll();
        }

        model.addAttribute("authors", authors);
        return "authors/list";
    }

    @GetMapping("/{id}")
    public String viewAuthor(@PathVariable Long id, Model model) {
        Optional<Author> author = authorService.findById(id);

        if (author.isPresent()) {
            model.addAttribute("author", author.get());
            return "authors/view";
        } else {
            return "redirect:/authors";
        }
    }

    @GetMapping("/new")
    public String createAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "authors/form";
    }

    @GetMapping("/{id}/edit")
    public String editAuthorForm(@PathVariable Long id, Model model) {
        Optional<Author> author = authorService.findById(id);

        if (author.isPresent()) {
            model.addAttribute("author", author.get());
            return "authors/form";
        } else {
            return "redirect:/authors";
        }
    }

    @PostMapping("/save")
    public String saveAuthor(@ModelAttribute Author author, RedirectAttributes redirectAttributes) {
        Author savedAuthor = authorService.save(author);
        redirectAttributes.addFlashAttribute("successMessage", "Автор успішно збережений");
        return "redirect:/authors/" + savedAuthor.getId();
    }

    @PostMapping("/{id}/delete")
    public String deleteAuthor(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (authorService.exists(id)) {
            authorService.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Автор успішно видалений");
        }
        return "redirect:/authors";
    }
}

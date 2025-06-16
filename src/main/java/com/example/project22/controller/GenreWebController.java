package com.example.project22.controller;

import com.example.project22.model.Genre;
import com.example.project22.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/genres")
public class GenreWebController {

    private final GenreService genreService;

    @Autowired
    public GenreWebController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public String listGenres(Model model) {
        List<Genre> genres = genreService.findAll();
        model.addAttribute("genres", genres);
        return "genres/list";
    }

    @GetMapping("/popular")
    public String listPopularGenres(Model model) {
        List<Genre> genres = genreService.findAllByPopularity();
        model.addAttribute("genres", genres);
        model.addAttribute("title", "Популярні жанри");
        return "genres/list";
    }

    @GetMapping("/search")
    public String searchGenres(@RequestParam(required = false) String name, Model model) {
        List<Genre> genres;

        if (name != null && !name.isEmpty()) {
            genres = genreService.findByNameContaining(name);
        } else {
            genres = genreService.findAll();
        }

        model.addAttribute("genres", genres);
        return "genres/list";
    }

    @GetMapping("/{id}")
    public String viewGenre(@PathVariable Long id, Model model) {
        Optional<Genre> genre = genreService.findById(id);

        if (genre.isPresent()) {
            model.addAttribute("genre", genre.get());
            return "genres/view";
        } else {
            return "redirect:/genres";
        }
    }

    @GetMapping("/new")
    public String createGenreForm(Model model) {
        model.addAttribute("genre", new Genre());
        return "genres/form";
    }

    @GetMapping("/{id}/edit")
    public String editGenreForm(@PathVariable Long id, Model model) {
        Optional<Genre> genre = genreService.findById(id);

        if (genre.isPresent()) {
            model.addAttribute("genre", genre.get());
            return "genres/form";
        } else {
            return "redirect:/genres";
        }
    }

    @PostMapping("/save")
    public String saveGenre(@ModelAttribute Genre genre, RedirectAttributes redirectAttributes) {
        // Перевірка на існування жанру з такою назвою
        if (genre.getId() == null && genreService.existsByName(genre.getName())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Жанр з такою назвою вже існує");
            return "redirect:/genres/new";
        }

        Genre savedGenre = genreService.save(genre);
        redirectAttributes.addFlashAttribute("successMessage", "Жанр успішно збережений");
        return "redirect:/genres";
    }

    @PostMapping("/{id}/delete")
    public String deleteGenre(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (genreService.exists(id)) {
            genreService.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Жанр успішно видалений");
        }
        return "redirect:/genres";
    }
}

package com.example.project22.controller;

import com.example.project22.model.Artist;
import com.example.project22.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/artists")
public class ArtistWebController {

    private final ArtistService artistService;

    @Autowired
    public ArtistWebController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    public String listArtists(Model model) {
        List<Artist> artists = artistService.findAll();
        model.addAttribute("artists", artists);
        return "artists/list";
    }

    @GetMapping("/search")
    public String searchArtists(@RequestParam(required = false) String name,
                               @RequestParam(required = false) String country,
                               Model model) {
        List<Artist> artists;

        if (name != null && !name.isEmpty()) {
            artists = artistService.findByName(name);
        } else if (country != null && !country.isEmpty()) {
            artists = artistService.findByCountry(country);
        } else {
            artists = artistService.findAll();
        }

        model.addAttribute("artists", artists);
        return "artists/list";
    }

    @GetMapping("/{id}")
    public String viewArtist(@PathVariable Long id, Model model) {
        Optional<Artist> artist = artistService.findById(id);

        if (artist.isPresent()) {
            model.addAttribute("artist", artist.get());
            return "artists/view";
        } else {
            return "redirect:/artists";
        }
    }

    @GetMapping("/new")
    public String createArtistForm(Model model) {
        model.addAttribute("artist", new Artist());
        return "artists/form";
    }

    @GetMapping("/{id}/edit")
    public String editArtistForm(@PathVariable Long id, Model model) {
        Optional<Artist> artist = artistService.findById(id);

        if (artist.isPresent()) {
            model.addAttribute("artist", artist.get());
            return "artists/form";
        } else {
            return "redirect:/artists";
        }
    }

    @PostMapping("/save")
    public String saveArtist(@ModelAttribute Artist artist, RedirectAttributes redirectAttributes) {
        Artist savedArtist = artistService.save(artist);
        redirectAttributes.addFlashAttribute("successMessage", "Виконавець успішно збережений");
        return "redirect:/artists/" + savedArtist.getId();
    }

    @PostMapping("/{id}/delete")
    public String deleteArtist(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (artistService.exists(id)) {
            artistService.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Виконавець успішно видалений");
        }
        return "redirect:/artists";
    }
}

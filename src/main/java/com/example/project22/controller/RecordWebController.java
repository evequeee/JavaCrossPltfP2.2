package com.example.project22.controller;

import com.example.project22.model.Artist;
import com.example.project22.model.Genre;
import com.example.project22.model.Publisher;
import com.example.project22.model.Record;
import com.example.project22.service.ArtistService;
import com.example.project22.service.GenreService;
import com.example.project22.service.PublisherService;
import com.example.project22.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/records")
public class RecordWebController {

    private final RecordService recordService;
    private final ArtistService artistService;
    private final PublisherService publisherService;
    private final GenreService genreService;

    @Autowired
    public RecordWebController(RecordService recordService,
                              ArtistService artistService,
                              PublisherService publisherService,
                              GenreService genreService) {
        this.recordService = recordService;
        this.artistService = artistService;
        this.publisherService = publisherService;
        this.genreService = genreService;
    }

    @GetMapping
    public String listRecords(Model model) {
        List<Record> records = recordService.findAll();
        model.addAttribute("records", records);
        return "records/list";
    }

    @GetMapping("/search")
    public String searchRecords(@RequestParam(required = false) String title,
                             @RequestParam(required = false) String artist,
                             @RequestParam(required = false) String genre,
                             Model model) {
        List<Record> records;

        if (title != null && !title.isEmpty()) {
            records = recordService.findByTitle(title);
        } else if (artist != null && !artist.isEmpty()) {
            // Можна реалізувати пошук за виконавцем, якщо потрібно
            records = recordService.findAll();
        } else if (genre != null && !genre.isEmpty()) {
            // Можна реалізувати пошук за жанром, якщо потрібно
            records = recordService.findAll();
        } else {
            records = recordService.findAll();
        }

        model.addAttribute("records", records);
        return "records/list";
    }

    @GetMapping("/{id}")
    public String viewRecord(@PathVariable Long id, Model model) {
        Optional<Record> record = recordService.findById(id);

        if (record.isPresent()) {
            model.addAttribute("record", record.get());
            return "records/view";
        } else {
            return "redirect:/records";
        }
    }

    @GetMapping("/new")
    public String showNewRecordForm(Model model) {
        model.addAttribute("record", new Record());
        model.addAttribute("artists", artistService.findAll());
        model.addAttribute("publishers", publisherService.findAll());
        model.addAttribute("genres", genreService.findAll());
        return "records/new";
    }

    @PostMapping
    public String createRecord(@ModelAttribute Record record,
                             @RequestParam(required = false) Long artistId,
                             @RequestParam(required = false) Long publisherId,
                             @RequestParam(required = false) List<Long> genreIds,
                             BindingResult result,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("artists", artistService.findAll());
            model.addAttribute("publishers", publisherService.findAll());
            model.addAttribute("genres", genreService.findAll());
            return "records/new";
        }

        // Set the artist if artistId is provided
        if (artistId != null) {
            Optional<Artist> artist = artistService.findById(artistId);
            artist.ifPresent(record::setArtist);
        }

        // Set the publisher if publisherId is provided
        if (publisherId != null) {
            Optional<Publisher> publisher = publisherService.findById(publisherId);
            publisher.ifPresent(record::setPublisher);
        }

        // Set genres if genreIds are provided
        if (genreIds != null && !genreIds.isEmpty()) {
            for (Long genreId : genreIds) {
                Optional<Genre> genre = genreService.findById(genreId);
                genre.ifPresent(g -> record.getGenres().add(g));
            }
        }

        Record savedRecord = recordService.save(record);
        redirectAttributes.addFlashAttribute("message", "Платівку успішно додано!");
        return "redirect:/records";
    }

    @GetMapping("/{id}/edit")
    public String showEditRecordForm(@PathVariable Long id, Model model) {
        Optional<Record> record = recordService.findById(id);

        if (record.isPresent()) {
            model.addAttribute("record", record.get());
            model.addAttribute("artists", artistService.findAll());
            model.addAttribute("publishers", publisherService.findAll());
            model.addAttribute("genres", genreService.findAll());
            return "records/edit";
        } else {
            return "redirect:/records";
        }
    }

    @PostMapping("/{id}")
    public String updateRecord(@PathVariable Long id,
                             @ModelAttribute Record record,
                             BindingResult result,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "records/edit";
        }

        Record updatedRecord = recordService.update(id, record);
        redirectAttributes.addFlashAttribute("message", "Платівку успішно оновлено!");
        return "redirect:/records";
    }

    @PostMapping("/{id}/delete")
    public String deleteRecord(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        boolean deleted = recordService.deleteById(id);

        if (deleted) {
            redirectAttributes.addFlashAttribute("message", "Платівку успішно видалено!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Помилка при видаленні платівки.");
        }

        return "redirect:/records";
    }
}

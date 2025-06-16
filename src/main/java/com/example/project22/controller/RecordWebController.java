package com.example.project22.controller;

import com.example.project22.model.Record;
import com.example.project22.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/records")
public class RecordWebController {

    private final RecordService recordService;

    @Autowired
    public RecordWebController(RecordService recordService) {
        this.recordService = recordService;
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
}

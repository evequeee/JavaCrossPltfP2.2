package com.example.project22.controller;

import com.example.project22.model.Publisher;
import com.example.project22.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public List<Publisher> getAllPublishers() {
        return publisherService.findAll();
    }

    @GetMapping("/by-book-count")
    public List<Publisher> getPublishersByBookCount() {
        return publisherService.findAllByBookCount();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publisher> getPublisherById(@PathVariable Long id) {
        return publisherService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/name")
    public ResponseEntity<Publisher> getPublisherByName(@RequestParam String name) {
        return publisherService.findByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<Publisher> searchPublishers(@RequestParam String query) {
        return publisherService.findByNameContaining(query);
    }

    @GetMapping("/established-after/{year}")
    public List<Publisher> getPublishersEstablishedAfter(@PathVariable Integer year) {
        return publisherService.findByEstablishedYearGreaterThan(year);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Publisher> createPublisher(@RequestBody Publisher publisher) {
        if (publisherService.existsByName(publisher.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(publisherService.save(publisher));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publisher> updatePublisher(@PathVariable Long id, @RequestBody Publisher publisher) {
        Publisher updatedPublisher = publisherService.update(id, publisher);
        return updatedPublisher != null
                ? ResponseEntity.ok(updatedPublisher)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id) {
        if (publisherService.exists(id)) {
            publisherService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

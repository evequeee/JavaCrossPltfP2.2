package com.example.project22.controller;

import com.example.project22.model.Record;
import com.example.project22.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/records")
public class RecordController {

    private final RecordService recordService;

    @Autowired
    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping
    public List<Record> getAllRecords() {
        return recordService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Record> getRecordById(@PathVariable Long id) {
        return recordService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/catalog/{catalogNumber}")
    public ResponseEntity<Record> getRecordByCatalogNumber(@PathVariable String catalogNumber) {
        return recordService.findByCatalogNumber(catalogNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/title")
    public List<Record> searchRecordsByTitle(@RequestParam String title) {
        return recordService.findByTitle(title);
    }

    @GetMapping("/search/artist/{artistId}")
    public List<Record> getRecordsByArtist(@PathVariable Long artistId) {
        return recordService.findByArtistId(artistId);
    }

    @GetMapping("/search/publisher/{publisherId}")
    public List<Record> getRecordsByPublisher(@PathVariable Long publisherId) {
        return recordService.findByPublisherId(publisherId);
    }

    @GetMapping("/search/genre/{genreId}")
    public List<Record> getRecordsByGenre(@PathVariable Long genreId) {
        return recordService.findByGenreId(genreId);
    }

    @GetMapping("/search/format")
    public List<Record> getRecordsByFormat(@RequestParam String format) {
        return recordService.findByFormat(format);
    }

    @GetMapping("/search/year/{year}")
    public List<Record> getRecordsByReleaseYear(@PathVariable Integer year) {
        return recordService.findByReleaseYear(year);
    }

    @GetMapping("/available")
    public List<Record> getAvailableRecords() {
        return recordService.findAvailableRecords();
    }

    @GetMapping("/search/price")
    public List<Record> getRecordsByPriceRange(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice) {
        return recordService.findByPriceRange(minPrice, maxPrice);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Record createRecord(@RequestBody Record record) {
        return recordService.save(record);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Record> updateRecord(@PathVariable Long id, @RequestBody Record record) {
        Record updatedRecord = recordService.update(id, record);
        return updatedRecord != null
                ? ResponseEntity.ok(updatedRecord)
                : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/copies")
    public ResponseEntity<Void> updateRecordCopies(
            @PathVariable Long id,
            @RequestParam int available,
            @RequestParam int total) {
        boolean updated = recordService.updateCopies(id, available, total);
        return updated
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        boolean deleted = recordService.deleteById(id);
        return deleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}

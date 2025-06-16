package com.example.project22.controller;

import com.example.project22.model.Author;
import com.example.project22.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        return authorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Author createAuthor(@RequestBody Author author) {
        return authorRepository.save(author);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author authorDetails) {
        return authorRepository.findById(id)
                .map(existingAuthor -> {
                    existingAuthor.setFirstName(authorDetails.getFirstName());
                    existingAuthor.setLastName(authorDetails.getLastName());
                    existingAuthor.setBirthDate(authorDetails.getBirthDate());
                    existingAuthor.setNationality(authorDetails.getNationality());
                    existingAuthor.setBiography(authorDetails.getBiography());
                    return ResponseEntity.ok(authorRepository.save(existingAuthor));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        return authorRepository.findById(id)
                .map(author -> {
                    authorRepository.delete(author);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Test endpoint to create a sample author
    @GetMapping("/test")
    public Author createTestAuthor() {
        Author author = new Author();
        author.setFirstName("Taras");
        author.setLastName("Shevchenko");
        author.setBirthDate(LocalDate.of(1814, 3, 9));
        author.setNationality("Ukrainian");
        author.setBiography("Taras Shevchenko was a Ukrainian poet, writer, artist, public and political figure, as well as folklorist and ethnographer.");
        return authorRepository.save(author);
    }
}

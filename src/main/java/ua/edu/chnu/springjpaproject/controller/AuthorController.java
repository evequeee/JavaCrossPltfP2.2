package ua.edu.chnu.springjpaproject.controller;

import ua.edu.chnu.springjpaproject.model.Author;
import ua.edu.chnu.springjpaproject.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/authors")
@CrossOrigin(origins = "*")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // GET /api/authors - Get all authors
    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

    // GET /api/authors/with-books - Get all authors with their books
    @GetMapping("/with-books")
    public ResponseEntity<List<Author>> getAllAuthorsWithBooks() {
        List<Author> authors = authorService.getAllAuthorsWithBooks();
        return ResponseEntity.ok(authors);
    }

    // GET /api/authors/{id} - Get author by ID
    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        Optional<Author> author = authorService.getAuthorById(id);
        return author.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/authors/{id}/with-books - Get author by ID with books
    @GetMapping("/{id}/with-books")
    public ResponseEntity<Author> getAuthorByIdWithBooks(@PathVariable Long id) {
        Optional<Author> author = authorService.getAuthorByIdWithBooks(id);
        return author.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/authors/search?lastName=... - Search authors by last name
    @GetMapping("/search")
    public ResponseEntity<List<Author>> searchAuthorsByLastName(@RequestParam String lastName) {
        List<Author> authors = authorService.searchAuthorsByLastName(lastName);
        return ResponseEntity.ok(authors);
    }

    // GET /api/authors/nationality/{nationality} - Get authors by nationality
    @GetMapping("/nationality/{nationality}")
    public ResponseEntity<List<Author>> getAuthorsByNationality(@PathVariable String nationality) {
        List<Author> authors = authorService.getAuthorsByNationality(nationality);
        return ResponseEntity.ok(authors);
    }

    // POST /api/authors - Create new author
    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        try {
            Author savedAuthor = authorService.saveAuthor(author);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAuthor);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // PUT /api/authors/{id} - Update author
    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        if (!authorService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        author.setId(id);
        try {
            Author updatedAuthor = authorService.updateAuthor(author);
            return ResponseEntity.ok(updatedAuthor);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // DELETE /api/authors/{id} - Delete author
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        if (!authorService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        try {
            authorService.deleteAuthor(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
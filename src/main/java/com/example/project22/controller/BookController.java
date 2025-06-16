package com.example.project22.controller;

import com.example.project22.model.Book;
import com.example.project22.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn) {
        return bookService.findByIsbn(isbn)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/title")
    public List<Book> searchBooksByTitle(@RequestParam String title) {
        return bookService.findByTitle(title);
    }

    @GetMapping("/search/author/{authorId}")
    public List<Book> getBooksByAuthor(@PathVariable Long authorId) {
        return bookService.findByAuthorId(authorId);
    }

    @GetMapping("/search/publisher/{publisherId}")
    public List<Book> getBooksByPublisher(@PathVariable Long publisherId) {
        return bookService.findByPublisherId(publisherId);
    }

    @GetMapping("/search/genre/{genreId}")
    public List<Book> getBooksByGenre(@PathVariable Long genreId) {
        return bookService.findByGenreId(genreId);
    }

    @GetMapping("/search/language")
    public List<Book> getBooksByLanguage(@RequestParam String language) {
        return bookService.findByLanguage(language);
    }

    @GetMapping("/search/year/{year}")
    public List<Book> getBooksByPublicationYear(@PathVariable Integer year) {
        return bookService.findByPublicationYear(year);
    }

    @GetMapping("/available")
    public List<Book> getAvailableBooks() {
        return bookService.findAvailableBooks();
    }

    @GetMapping("/search/price")
    public List<Book> getBooksByPriceRange(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice) {
        return bookService.findByPriceRange(minPrice, maxPrice);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updatedBook = bookService.update(id, book);
        return updatedBook != null
                ? ResponseEntity.ok(updatedBook)
                : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/copies")
    public ResponseEntity<Void> updateBookCopies(
            @PathVariable Long id,
            @RequestParam int available,
            @RequestParam int total) {
        boolean updated = bookService.updateBookCopies(id, available, total);
        return updated
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookService.exists(id)) {
            bookService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

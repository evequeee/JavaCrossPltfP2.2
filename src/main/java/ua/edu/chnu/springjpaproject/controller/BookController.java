package ua.edu.chnu.springjpaproject.controller;

import ua.edu.chnu.springjpaproject.model.Book;
import ua.edu.chnu.springjpaproject.model.Author;
import ua.edu.chnu.springjpaproject.model.Category;
import ua.edu.chnu.springjpaproject.service.BookService;
import ua.edu.chnu.springjpaproject.service.AuthorService;
import ua.edu.chnu.springjpaproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Autowired
    public BookController(BookService bookService,
                          AuthorService authorService,
                          CategoryService categoryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    // GET /api/books - Get all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    // GET /api/books/with-details - Get all books with author and category
    @GetMapping("/with-details")
    public ResponseEntity<List<Book>> getAllBooksWithDetails() {
        List<Book> books = bookService.getAllBooksWithAuthorAndCategory();
        return ResponseEntity.ok(books);
    }

    // GET /api/books/{id} - Get book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/books/{id}/with-details - Get book by ID with author and category
    @GetMapping("/{id}/with-details")
    public ResponseEntity<Book> getBookByIdWithDetails(@PathVariable Long id) {
        Optional<Book> book = bookService.getBookByIdWithAuthorAndCategory(id);
        return book.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/books/search?title=... - Search books by title
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooksByTitle(@RequestParam String title) {
        List<Book> books = bookService.searchBooksByTitle(title);
        return ResponseEntity.ok(books);
    }

    // GET /api/books/search-all?q=... - Search books by any criteria
    @GetMapping("/search-all")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam String q) {
        List<Book> books = bookService.searchBooks(q);
        return ResponseEntity.ok(books);
    }

    // GET /api/books/isbn/{isbn} - Find book by ISBN
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Book> findBookByIsbn(@PathVariable String isbn) {
        Optional<Book> book = bookService.findBookByIsbn(isbn);
        return book.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/books/author/{authorId} - Get books by author
    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable Long authorId) {
        Optional<Author> author = authorService.getAuthorById(authorId);
        if (author.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<Book> books = bookService.getBooksByAuthor(author.get());
        return ResponseEntity.ok(books);
    }

    // GET /api/books/category/{categoryId} - Get books by category
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Book>> getBooksByCategory(@PathVariable Long categoryId) {
        Optional<Category> category = categoryService.getCategoryById(categoryId);
        if (category.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<Book> books = bookService.getBooksByCategory(category.get());
        return ResponseEntity.ok(books);
    }

    // GET /api/books/year/{year} - Get books by publication year
    @GetMapping("/year/{year}")
    public ResponseEntity<List<Book>> getBooksByYear(@PathVariable Integer year) {
        List<Book> books = bookService.getBooksByPublicationYear(year);
        return ResponseEntity.ok(books);
    }

    // GET /api/books/year-range?start=...&end=... - Get books by year range
    @GetMapping("/year-range")
    public ResponseEntity<List<Book>> getBooksByYearRange(@RequestParam Integer start,
                                                          @RequestParam Integer end) {
        List<Book> books = bookService.getBooksByPublicationYearRange(start, end);
        return ResponseEntity.ok(books);
    }

    // POST /api/books - Create new book
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        try {
            // Validate author exists
            if (book.getAuthor() == null || !authorService.existsById(book.getAuthor().getId())) {
                return ResponseEntity.badRequest().build();
            }

            // Validate category exists
            if (book.getCategory() == null || !categoryService.existsById(book.getCategory().getId())) {
                return ResponseEntity.badRequest().build();
            }

            // Check if ISBN already exists
            if (book.getIsbn() != null && bookService.existsByIsbn(book.getIsbn())) {
                return ResponseEntity.badRequest().build();
            }

            Book savedBook = bookService.saveBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // PUT /api/books/{id} - Update book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        if (!bookService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        // Validate author exists
        if (book.getAuthor() == null || !authorService.existsById(book.getAuthor().getId())) {
            return ResponseEntity.badRequest().build();
        }

        // Validate category exists
        if (book.getCategory() == null || !categoryService.existsById(book.getCategory().getId())) {
            return ResponseEntity.badRequest().build();
        }

        book.setId(id);
        try {
            Book updatedBook = bookService.updateBook(book);
            return ResponseEntity.ok(updatedBook);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // DELETE /api/books/{id} - Delete book
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (!bookService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        try {
            bookService.deleteBook(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
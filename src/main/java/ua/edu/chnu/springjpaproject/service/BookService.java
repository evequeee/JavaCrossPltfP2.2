package ua.edu.chnu.springjpaproject.service;

import ua.edu.chnu.springjpaproject.model.Book;
import ua.edu.chnu.springjpaproject.model.Author;
import ua.edu.chnu.springjpaproject.model.Category;
import ua.edu.chnu.springjpaproject.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Get all books
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Get all books with author and category
    @Transactional(readOnly = true)
    public List<Book> getAllBooksWithAuthorAndCategory() {
        return bookRepository.findAllWithAuthorAndCategory();
    }

    // Get book by ID
    @Transactional(readOnly = true)
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    // Get book by ID with author and category
    @Transactional(readOnly = true)
    public Optional<Book> getBookByIdWithAuthorAndCategory(Long id) {
        return bookRepository.findByIdWithAuthorAndCategory(id);
    }

    // Find book by ISBN
    @Transactional(readOnly = true)
    public Optional<Book> findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    // Search books by title
    @Transactional(readOnly = true)
    public List<Book> searchBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    // Get books by author
    @Transactional(readOnly = true)
    public List<Book> getBooksByAuthor(Author author) {
        return bookRepository.findByAuthor(author);
    }

    // Get books by category
    @Transactional(readOnly = true)
    public List<Book> getBooksByCategory(Category category) {
        return bookRepository.findByCategory(category);
    }

    // Get books by publication year
    @Transactional(readOnly = true)
    public List<Book> getBooksByPublicationYear(Integer year) {
        return bookRepository.findByPublicationYear(year);
    }

    // Get books by publication year range
    @Transactional(readOnly = true)
    public List<Book> getBooksByPublicationYearRange(Integer startYear, Integer endYear) {
        return bookRepository.findByPublicationYearBetween(startYear, endYear);
    }

    // Search books by any criteria
    @Transactional(readOnly = true)
    public List<Book> searchBooks(String searchTerm) {
        return bookRepository.searchBooks(searchTerm);
    }

    // Save book
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    // Update book
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    // Delete book by ID
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    // Check if book exists
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return bookRepository.existsById(id);
    }

    // Check if ISBN exists
    @Transactional(readOnly = true)
    public boolean existsByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn).isPresent();
    }
}
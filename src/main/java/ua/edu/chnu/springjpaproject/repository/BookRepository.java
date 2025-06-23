package ua.edu.chnu.springjpaproject.repository;

import ua.edu.chnu.springjpaproject.model.Book;
import ua.edu.chnu.springjpaproject.model.Author;
import ua.edu.chnu.springjpaproject.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Find books by title
    List<Book> findByTitleContainingIgnoreCase(String title);

    // Find books by author
    List<Book> findByAuthor(Author author);

    // Find books by category
    List<Book> findByCategory(Category category);

    // Find book by ISBN
    Optional<Book> findByIsbn(String isbn);

    // Find books by publication year
    List<Book> findByPublicationYear(Integer year);

    // Find books by publication year range
    List<Book> findByPublicationYearBetween(Integer startYear, Integer endYear);

    // Custom query to find books with author and category
    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.author LEFT JOIN FETCH b.category")
    List<Book> findAllWithAuthorAndCategory();

    // Find book by id with author and category
    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.author LEFT JOIN FETCH b.category WHERE b.id = :id")
    Optional<Book> findByIdWithAuthorAndCategory(@Param("id") Long id);

    // Search books by title, author name or category
    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.author a LEFT JOIN FETCH b.category c " +
            "WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(a.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(a.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(c.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Book> searchBooks(@Param("searchTerm") String searchTerm);
}
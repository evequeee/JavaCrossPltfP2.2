package ua.edu.chnu.springjpaproject.repository;

import ua.edu.chnu.springjpaproject.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    // Find authors by last name
    List<Author> findByLastNameContainingIgnoreCase(String lastName);

    // Find authors by nationality
    List<Author> findByNationalityIgnoreCase(String nationality);

    // Find author by full name
    Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);

    // Custom query to find authors with books count
    @Query("SELECT a FROM Author a LEFT JOIN FETCH a.books WHERE a.id = :id")
    Optional<Author> findByIdWithBooks(@Param("id") Long id);

    // Find all authors with their books
    @Query("SELECT DISTINCT a FROM Author a LEFT JOIN FETCH a.books")
    List<Author> findAllWithBooks();
}
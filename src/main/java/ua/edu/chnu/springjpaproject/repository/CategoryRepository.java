package ua.edu.chnu.springjpaproject.repository;

import ua.edu.chnu.springjpaproject.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Find category by name
    Optional<Category> findByNameIgnoreCase(String name);

    // Find categories by name containing text
    List<Category> findByNameContainingIgnoreCase(String name);

    // Custom query to find category with books
    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.books WHERE c.id = :id")
    Optional<Category> findByIdWithBooks(@Param("id") Long id);

    // Find all categories with their books count
    @Query("SELECT c FROM Category c LEFT JOIN c.books b GROUP BY c.id ORDER BY COUNT(b) DESC")
    List<Category> findAllOrderByBooksCount();
}
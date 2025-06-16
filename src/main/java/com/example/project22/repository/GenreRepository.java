package com.example.project22.repository;

import com.example.project22.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    Optional<Genre> findByNameIgnoreCase(String name);

    List<Genre> findByNameContainingIgnoreCase(String nameFragment);

    @Query("SELECT g FROM Genre g JOIN g.books b GROUP BY g ORDER BY COUNT(b) DESC")
    List<Genre> findAllOrderByPopularity();
}

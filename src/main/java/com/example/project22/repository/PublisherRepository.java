package com.example.project22.repository;

import com.example.project22.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    Optional<Publisher> findByNameIgnoreCase(String name);

    List<Publisher> findByNameContainingIgnoreCase(String nameFragment);

    List<Publisher> findByEstablishedYearGreaterThan(Integer year);

    @Query("SELECT p FROM Publisher p JOIN p.books b GROUP BY p ORDER BY COUNT(b) DESC")
    List<Publisher> findAllOrderByBookCount();
}

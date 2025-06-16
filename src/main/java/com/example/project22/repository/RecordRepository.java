package com.example.project22.repository;

import com.example.project22.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
    List<Record> findByTitleContainingIgnoreCase(String title);
    Optional<Record> findByCatalogNumber(String catalogNumber);
    List<Record> findByArtistId(Long artistId);
    List<Record> findByPublisherId(Long publisherId);
    List<Record> findByFormat(String format);
    List<Record> findByReleaseYear(Integer year);

    @Query("SELECT r FROM Record r WHERE r.availableCopies > 0")
    List<Record> findAvailableRecords();

    @Query("SELECT r FROM Record r WHERE r.price BETWEEN ?1 AND ?2")
    List<Record> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);

    @Query("SELECT r FROM Record r JOIN r.genres g WHERE g.id = ?1")
    List<Record> findByGenreId(Long genreId);
}

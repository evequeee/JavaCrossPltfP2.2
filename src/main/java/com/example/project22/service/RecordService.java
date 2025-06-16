package com.example.project22.service;

import com.example.project22.model.Record;
import com.example.project22.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class RecordService {

    private final RecordRepository recordRepository;

    @Autowired
    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public List<Record> findAll() {
        return recordRepository.findAll();
    }

    public Optional<Record> findById(Long id) {
        return recordRepository.findById(id);
    }

    public Optional<Record> findByCatalogNumber(String catalogNumber) {
        return recordRepository.findByCatalogNumber(catalogNumber);
    }

    public List<Record> findByTitle(String title) {
        return recordRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Record> findByArtistId(Long artistId) {
        return recordRepository.findByArtistId(artistId);
    }

    public List<Record> findByPublisherId(Long publisherId) {
        return recordRepository.findByPublisherId(publisherId);
    }

    public List<Record> findByGenreId(Long genreId) {
        return recordRepository.findByGenreId(genreId);
    }

    public List<Record> findByFormat(String format) {
        return recordRepository.findByFormat(format);
    }

    public List<Record> findByReleaseYear(Integer year) {
        return recordRepository.findByReleaseYear(year);
    }

    public List<Record> findAvailableRecords() {
        return recordRepository.findAvailableRecords();
    }

    public List<Record> findByPriceRange(Double minPrice, Double maxPrice) {
        return recordRepository.findByPriceRange(
                BigDecimal.valueOf(minPrice),
                BigDecimal.valueOf(maxPrice)
        );
    }

    public Record save(Record record) {
        return recordRepository.save(record);
    }

    public Record update(Long id, Record recordDetails) {
        return recordRepository.findById(id)
                .map(existingRecord -> {
                    existingRecord.setTitle(recordDetails.getTitle());
                    existingRecord.setCatalogNumber(recordDetails.getCatalogNumber());
                    existingRecord.setReleaseYear(recordDetails.getReleaseYear());
                    existingRecord.setTrackCount(recordDetails.getTrackCount());
                    existingRecord.setFormat(recordDetails.getFormat());
                    existingRecord.setDescription(recordDetails.getDescription());
                    existingRecord.setCoverImageUrl(recordDetails.getCoverImageUrl());
                    existingRecord.setPrice(recordDetails.getPrice());
                    existingRecord.setArtist(recordDetails.getArtist());
                    existingRecord.setPublisher(recordDetails.getPublisher());
                    existingRecord.setGenres(recordDetails.getGenres());
                    return recordRepository.save(existingRecord);
                })
                .orElse(null);
    }

    public boolean updateCopies(Long id, int availableCopies, int totalCopies) {
        return recordRepository.findById(id)
                .map(record -> {
                    record.setAvailableCopies(availableCopies);
                    record.setTotalCopies(totalCopies);
                    recordRepository.save(record);
                    return true;
                })
                .orElse(false);
    }

    public boolean deleteById(Long id) {
        if (recordRepository.existsById(id)) {
            recordRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

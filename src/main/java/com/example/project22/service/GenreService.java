package com.example.project22.service;

import com.example.project22.model.Genre;
import com.example.project22.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    public List<Genre> findAllByPopularity() {
        return genreRepository.findAllOrderByPopularity();
    }

    public Optional<Genre> findById(Long id) {
        return genreRepository.findById(id);
    }

    public Optional<Genre> findByName(String name) {
        return genreRepository.findByNameIgnoreCase(name);
    }

    public List<Genre> findByNameContaining(String nameFragment) {
        return genreRepository.findByNameContainingIgnoreCase(nameFragment);
    }

    @Transactional
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    @Transactional
    public Genre update(Long id, Genre genreDetails) {
        return genreRepository.findById(id)
                .map(existingGenre -> {
                    existingGenre.setName(genreDetails.getName());
                    existingGenre.setDescription(genreDetails.getDescription());
                    return genreRepository.save(existingGenre);
                })
                .orElse(null);
    }

    @Transactional
    public void delete(Long id) {
        genreRepository.findById(id).ifPresent(genreRepository::delete);
    }

    @Transactional
    public boolean exists(Long id) {
        return genreRepository.existsById(id);
    }

    @Transactional
    public boolean existsByName(String name) {
        return genreRepository.findByNameIgnoreCase(name).isPresent();
    }
}

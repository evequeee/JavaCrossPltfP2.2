package com.example.project22.service;

import com.example.project22.model.Artist;
import com.example.project22.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<Artist> findAll() {
        return artistRepository.findAll();
    }

    public Optional<Artist> findById(Long id) {
        return artistRepository.findById(id);
    }

    public List<Artist> findByName(String name) {
        return artistRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Artist> findByCountry(String country) {
        return artistRepository.findByCountryContainingIgnoreCase(country);
    }

    public Artist save(Artist artist) {
        return artistRepository.save(artist);
    }

    public Artist update(Long id, Artist artistDetails) {
        return artistRepository.findById(id)
                .map(existingArtist -> {
                    existingArtist.setName(artistDetails.getName());
                    existingArtist.setCountry(artistDetails.getCountry());
                    existingArtist.setFormationDate(artistDetails.getFormationDate());
                    existingArtist.setBiography(artistDetails.getBiography());
                    // Save the updated artist back to the repository
                    return artistRepository.save(existingArtist);
                })
                .orElseThrow(() -> new RuntimeException("Artist not found with id " + id));
    }

    public boolean deleteById(Long id) {
        if (artistRepository.existsById(id)) {
            artistRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean exists(Long id) {
        return artistRepository.existsById(id);
    }

    public void delete(Long id) {
        artistRepository.deleteById(id);
    }
}

package com.example.project22.service;

import com.example.project22.model.Author;
import com.example.project22.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    
    private final AuthorRepository authorRepository;
    
    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    
    public List<Author> findAll() {
        return authorRepository.findAll();
    }
    
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }
    
    public List<Author> findByLastName(String lastName) {
        return authorRepository.findByLastNameContainingIgnoreCase(lastName);
    }
    
    public List<Author> findByNationality(String nationality) {
        return authorRepository.findByNationalityIgnoreCase(nationality);
    }
    
    @Transactional
    public Author save(Author author) {
        return authorRepository.save(author);
    }
    
    @Transactional
    public Author update(Long id, Author authorDetails) {
        return authorRepository.findById(id)
                .map(existingAuthor -> {
                    existingAuthor.setFirstName(authorDetails.getFirstName());
                    existingAuthor.setLastName(authorDetails.getLastName());
                    existingAuthor.setBirthDate(authorDetails.getBirthDate());
                    existingAuthor.setNationality(authorDetails.getNationality());
                    existingAuthor.setBiography(authorDetails.getBiography());
                    return authorRepository.save(existingAuthor);
                })
                .orElse(null);
    }
    
    @Transactional
    public void delete(Long id) {
        authorRepository.findById(id).ifPresent(authorRepository::delete);
    }
    
    @Transactional
    public boolean exists(Long id) {
        return authorRepository.existsById(id);
    }
}

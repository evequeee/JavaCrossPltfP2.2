package ua.edu.chnu.springjpaproject.service;

import ua.edu.chnu.springjpaproject.model.Author;
import ua.edu.chnu.springjpaproject.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    // Get all authors
    @Transactional(readOnly = true)
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    // Get all authors with books
    @Transactional(readOnly = true)
    public List<Author> getAllAuthorsWithBooks() {
        return authorRepository.findAllWithBooks();
    }

    // Get author by ID
    @Transactional(readOnly = true)
    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    // Get author by ID with books
    @Transactional(readOnly = true)
    public Optional<Author> getAuthorByIdWithBooks(Long id) {
        return authorRepository.findByIdWithBooks(id);
    }

    // Search authors by last name
    @Transactional(readOnly = true)
    public List<Author> searchAuthorsByLastName(String lastName) {
        return authorRepository.findByLastNameContainingIgnoreCase(lastName);
    }

    // Find authors by nationality
    @Transactional(readOnly = true)
    public List<Author> getAuthorsByNationality(String nationality) {
        return authorRepository.findByNationalityIgnoreCase(nationality);
    }

    // Find author by full name
    @Transactional(readOnly = true)
    public Optional<Author> findAuthorByFullName(String firstName, String lastName) {
        return authorRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    // Save author
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    // Update author
    public Author updateAuthor(Author author) {
        return authorRepository.save(author);
    }

    // Delete author by ID
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    // Check if author exists
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return authorRepository.existsById(id);
    }
}
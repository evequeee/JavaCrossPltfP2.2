package com.example.project22.service;

import com.example.project22.model.Book;
import com.example.project22.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public Optional<Book> findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Book> findByAuthorId(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }

    public List<Book> findByPublisherId(Long publisherId) {
        return bookRepository.findByPublisherId(publisherId);
    }

    public List<Book> findByGenreId(Long genreId) {
        return bookRepository.findByGenreId(genreId);
    }

    public List<Book> findByLanguage(String language) {
        return bookRepository.findByLanguageIgnoreCase(language);
    }

    public List<Book> findByPublicationYear(Integer year) {
        return bookRepository.findByPublicationYear(year);
    }

    public List<Book> findAvailableBooks() {
        return bookRepository.findAvailableBooks();
    }

    public List<Book> findByPriceRange(Double minPrice, Double maxPrice) {
        return bookRepository.findByPriceRange(minPrice, maxPrice);
    }

    @Transactional
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public Book update(Long id, Book bookDetails) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    existingBook.setTitle(bookDetails.getTitle());
                    existingBook.setIsbn(bookDetails.getIsbn());
                    existingBook.setPublicationYear(bookDetails.getPublicationYear());
                    existingBook.setPages(bookDetails.getPages());
                    existingBook.setLanguage(bookDetails.getLanguage());
                    existingBook.setDescription(bookDetails.getDescription());
                    existingBook.setCoverImageUrl(bookDetails.getCoverImageUrl());
                    existingBook.setPrice(bookDetails.getPrice());
                    existingBook.setAuthor(bookDetails.getAuthor());
                    existingBook.setPublisher(bookDetails.getPublisher());
                    existingBook.setGenres(bookDetails.getGenres());
                    return bookRepository.save(existingBook);
                })
                .orElse(null);
    }

    @Transactional
    public void delete(Long id) {
        bookRepository.findById(id).ifPresent(bookRepository::delete);
    }

    @Transactional
    public boolean exists(Long id) {
        return bookRepository.existsById(id);
    }

    @Transactional
    public boolean updateBookCopies(Long bookId, int availableCopies, int totalCopies) {
        return bookRepository.findById(bookId)
                .map(book -> {
                    if (availableCopies <= totalCopies) {
                        book.setAvailableCopies(availableCopies);
                        book.setTotalCopies(totalCopies);
                        bookRepository.save(book);
                        return true;
                    }
                    return false;
                })
                .orElse(false);
    }

    @Transactional
    public boolean decrementAvailableCopies(Long bookId) {
        return bookRepository.findById(bookId)
                .map(book -> {
                    if (book.getAvailableCopies() > 0) {
                        book.setAvailableCopies(book.getAvailableCopies() - 1);
                        bookRepository.save(book);
                        return true;
                    }
                    return false;
                })
                .orElse(false);
    }

    @Transactional
    public boolean incrementAvailableCopies(Long bookId) {
        return bookRepository.findById(bookId)
                .map(book -> {
                    if (book.getAvailableCopies() < book.getTotalCopies()) {
                        book.setAvailableCopies(book.getAvailableCopies() + 1);
                        bookRepository.save(book);
                        return true;
                    }
                    return false;
                })
                .orElse(false);
    }
}

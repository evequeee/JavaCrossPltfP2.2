package com.example.project22.service;

import com.example.project22.model.Book;
import com.example.project22.model.BookLoan;
import com.example.project22.model.LibraryUser;
import com.example.project22.repository.BookLoanRepository;
import com.example.project22.repository.BookRepository;
import com.example.project22.repository.LibraryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class BookLoanService {

    private final BookLoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final LibraryUserRepository userRepository;

    @Autowired
    public BookLoanService(
            BookLoanRepository loanRepository,
            BookRepository bookRepository,
            LibraryUserRepository userRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public List<BookLoan> findAll() {
        return loanRepository.findAll();
    }

    public Optional<BookLoan> findById(Long id) {
        return loanRepository.findById(id);
    }

    public List<BookLoan> findByBookId(Long bookId) {
        return loanRepository.findByBookId(bookId);
    }

    public List<BookLoan> findByUserId(Long userId) {
        return loanRepository.findByUserId(userId);
    }

    public List<BookLoan> findActiveLoansForUser(Long userId) {
        return loanRepository.findActiveLoansForUser(userId);
    }

    public List<BookLoan> findActiveLoansForBook(Long bookId) {
        return loanRepository.findActiveLoansForBook(bookId);
    }

    public List<BookLoan> findOverdueLoans() {
        return loanRepository.findByDueDateBeforeAndIsReturnedFalse(LocalDate.now());
    }

    public List<BookLoan> findLateReturns() {
        return loanRepository.findLateReturns();
    }

    public List<BookLoan> findByReturnStatus(boolean isReturned) {
        return loanRepository.findByIsReturned(isReturned);
    }

    public List<BookLoan> findByDateRange(LocalDate startDate, LocalDate endDate) {
        return loanRepository.findLoansByDateRange(startDate, endDate);
    }

    @Transactional
    public BookLoan borrowBook(Long bookId, Long userId, LocalDate dueDate) {
        Optional<Book> bookOpt = bookRepository.findById(bookId);
        Optional<LibraryUser> userOpt = userRepository.findById(userId);

        if (bookOpt.isPresent() && userOpt.isPresent()) {
            Book book = bookOpt.get();
            LibraryUser user = userOpt.get();

            // Check if user is active
            if (!user.getIsActive()) {
                return null; // User is not active
            }

            // Check if membership has expired
            if (user.getMembershipExpires() != null &&
                user.getMembershipExpires().isBefore(LocalDate.now())) {
                return null; // Membership expired
            }

            // Check if the book has available copies
            if (book.getAvailableCopies() <= 0) {
                return null; // No copies available
            }

            // Decrement available copies
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            bookRepository.save(book);

            // Create new loan
            BookLoan loan = new BookLoan();
            loan.setBook(book);
            loan.setUser(user);
            loan.setLoanDate(LocalDate.now());
            loan.setDueDate(dueDate);
            loan.setIsReturned(false);
            loan.setConditionOnLoan("Good");

            return loanRepository.save(loan);
        }

        return null;
    }

    @Transactional
    public BookLoan returnBook(Long loanId, String condition) {
        return loanRepository.findById(loanId)
                .map(loan -> {
                    if (!loan.getIsReturned()) {
                        // Update the loan
                        loan.setReturnDate(LocalDate.now());
                        loan.setIsReturned(true);
                        loan.setConditionOnReturn(condition);

                        // Calculate late fee if returned after due date
                        if (loan.getReturnDate().isAfter(loan.getDueDate())) {
                            long daysLate = ChronoUnit.DAYS.between(loan.getDueDate(), loan.getReturnDate());
                            // Assuming a late fee of $0.50 per day
                            BigDecimal lateFee = new BigDecimal(daysLate * 0.5);
                            loan.setLateFee(lateFee);
                        }

                        // Increment available copies for the book
                        Book book = loan.getBook();
                        book.setAvailableCopies(book.getAvailableCopies() + 1);
                        bookRepository.save(book);

                        return loanRepository.save(loan);
                    }
                    return loan; // Already returned
                })
                .orElse(null);
    }

    @Transactional
    public BookLoan extendLoan(Long loanId, LocalDate newDueDate) {
        return loanRepository.findById(loanId)
                .map(loan -> {
                    if (!loan.getIsReturned() && !LocalDate.now().isAfter(loan.getDueDate())) {
                        loan.setDueDate(newDueDate);
                        return loanRepository.save(loan);
                    }
                    return null; // Cannot extend if already returned or overdue
                })
                .orElse(null);
    }

    @Transactional
    public boolean updateLateFee(Long loanId, BigDecimal newFee) {
        return loanRepository.findById(loanId)
                .map(loan -> {
                    loan.setLateFee(newFee);
                    loanRepository.save(loan);
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    public void delete(Long id) {
        loanRepository.findById(id).ifPresent(loanRepository::delete);
    }

    @Transactional
    public boolean exists(Long id) {
        return loanRepository.existsById(id);
    }
}

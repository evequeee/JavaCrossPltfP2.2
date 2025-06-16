package com.example.project22.controller;

import com.example.project22.model.BookLoan;
import com.example.project22.service.BookLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class BookLoanController {

    private final BookLoanService loanService;

    @Autowired
    public BookLoanController(BookLoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public List<BookLoan> getAllLoans() {
        return loanService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookLoan> getLoanById(@PathVariable Long id) {
        return loanService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/book/{bookId}")
    public List<BookLoan> getLoansByBook(@PathVariable Long bookId) {
        return loanService.findByBookId(bookId);
    }

    @GetMapping("/user/{userId}")
    public List<BookLoan> getLoansByUser(@PathVariable Long userId) {
        return loanService.findByUserId(userId);
    }

    @GetMapping("/active/book/{bookId}")
    public List<BookLoan> getActiveLoansForBook(@PathVariable Long bookId) {
        return loanService.findActiveLoansForBook(bookId);
    }

    @GetMapping("/active/user/{userId}")
    public List<BookLoan> getActiveLoansForUser(@PathVariable Long userId) {
        return loanService.findActiveLoansForUser(userId);
    }

    @GetMapping("/overdue")
    public List<BookLoan> getOverdueLoans() {
        return loanService.findOverdueLoans();
    }

    @GetMapping("/late-returns")
    public List<BookLoan> getLateReturns() {
        return loanService.findLateReturns();
    }

    @GetMapping("/status")
    public List<BookLoan> getLoansByStatus(@RequestParam boolean returned) {
        return loanService.findByReturnStatus(returned);
    }

    @GetMapping("/date-range")
    public List<BookLoan> getLoansByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return loanService.findByDateRange(startDate, endDate);
    }

    @PostMapping("/borrow")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookLoan> borrowBook(
            @RequestParam Long bookId,
            @RequestParam Long userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate) {
        BookLoan loan = loanService.borrowBook(bookId, userId, dueDate);
        return loan != null
                ? ResponseEntity.status(HttpStatus.CREATED).body(loan)
                : ResponseEntity.badRequest().build();
    }

    @PatchMapping("/{id}/return")
    public ResponseEntity<BookLoan> returnBook(
            @PathVariable Long id,
            @RequestParam String condition) {
        BookLoan returnedLoan = loanService.returnBook(id, condition);
        return returnedLoan != null
                ? ResponseEntity.ok(returnedLoan)
                : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/extend")
    public ResponseEntity<BookLoan> extendLoan(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate newDueDate) {
        BookLoan extendedLoan = loanService.extendLoan(id, newDueDate);
        return extendedLoan != null
                ? ResponseEntity.ok(extendedLoan)
                : ResponseEntity.badRequest().build();
    }

    @PatchMapping("/{id}/late-fee")
    public ResponseEntity<Void> updateLateFee(
            @PathVariable Long id,
            @RequestParam BigDecimal fee) {
        boolean updated = loanService.updateLateFee(id, fee);
        return updated
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id) {
        if (loanService.exists(id)) {
            loanService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

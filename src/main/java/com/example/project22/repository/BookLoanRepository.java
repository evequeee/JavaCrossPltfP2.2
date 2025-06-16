package com.example.project22.repository;

import com.example.project22.model.BookLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookLoanRepository extends JpaRepository<BookLoan, Long> {

    List<BookLoan> findByBookId(Long bookId);

    List<BookLoan> findByUserId(Long userId);

    List<BookLoan> findByIsReturned(Boolean isReturned);

    List<BookLoan> findByDueDateBeforeAndIsReturnedFalse(LocalDate date);

    @Query("SELECT bl FROM BookLoan bl WHERE bl.user.id = :userId AND bl.isReturned = false")
    List<BookLoan> findActiveLoansForUser(@Param("userId") Long userId);

    @Query("SELECT bl FROM BookLoan bl WHERE bl.returnDate IS NOT NULL AND bl.returnDate > bl.dueDate")
    List<BookLoan> findLateReturns();

    @Query("SELECT bl FROM BookLoan bl WHERE bl.book.id = :bookId AND bl.isReturned = false")
    List<BookLoan> findActiveLoansForBook(@Param("bookId") Long bookId);

    @Query("SELECT bl FROM BookLoan bl WHERE " +
           "bl.loanDate BETWEEN :startDate AND :endDate " +
           "ORDER BY bl.loanDate DESC")
    List<BookLoan> findLoansByDateRange(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
}

package com.example.project22.repository;

import com.example.project22.model.RecordLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RecordLoanRepository extends JpaRepository<RecordLoan, Long> {
    List<RecordLoan> findByUserId(Long userId);
    List<RecordLoan> findByRecordId(Long recordId);
    List<RecordLoan> findByIsReturned(Boolean isReturned);

    @Query("SELECT l FROM RecordLoan l WHERE l.dueDate < CURRENT_DATE AND l.isReturned = false")
    List<RecordLoan> findOverdueLoans();

    @Query("SELECT l FROM RecordLoan l WHERE l.loanDate BETWEEN ?1 AND ?2")
    List<RecordLoan> findByLoanDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT l FROM RecordLoan l WHERE l.returnDate BETWEEN ?1 AND ?2")
    List<RecordLoan> findByReturnDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT l FROM RecordLoan l WHERE l.user.id = ?1 AND l.isReturned = false")
    List<RecordLoan> findActiveLoansForUser(Long userId);
}

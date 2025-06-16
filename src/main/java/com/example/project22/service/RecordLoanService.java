package com.example.project22.service;

import com.example.project22.model.Record;
import com.example.project22.model.RecordLoan;
import com.example.project22.model.LibraryUser;
import com.example.project22.repository.RecordLoanRepository;
import com.example.project22.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class RecordLoanService {

    private final RecordLoanRepository recordLoanRepository;
    private final RecordRepository recordRepository;

    @Autowired
    public RecordLoanService(RecordLoanRepository recordLoanRepository, RecordRepository recordRepository) {
        this.recordLoanRepository = recordLoanRepository;
        this.recordRepository = recordRepository;
    }

    public List<RecordLoan> findAll() {
        return recordLoanRepository.findAll();
    }

    public Optional<RecordLoan> findById(Long id) {
        return recordLoanRepository.findById(id);
    }

    public List<RecordLoan> findByUserId(Long userId) {
        return recordLoanRepository.findByUserId(userId);
    }

    public List<RecordLoan> findByRecordId(Long recordId) {
        return recordLoanRepository.findByRecordId(recordId);
    }

    public List<RecordLoan> findActiveLoans() {
        return recordLoanRepository.findByIsReturned(false);
    }

    public List<RecordLoan> findReturnedLoans() {
        return recordLoanRepository.findByIsReturned(true);
    }

    public List<RecordLoan> findOverdueLoans() {
        return recordLoanRepository.findOverdueLoans();
    }

    public List<RecordLoan> findByLoanDateRange(LocalDate startDate, LocalDate endDate) {
        return recordLoanRepository.findByLoanDateBetween(startDate, endDate);
    }

    public List<RecordLoan> findByReturnDateRange(LocalDate startDate, LocalDate endDate) {
        return recordLoanRepository.findByReturnDateBetween(startDate, endDate);
    }

    public List<RecordLoan> findActiveLoansForUser(Long userId) {
        return recordLoanRepository.findActiveLoansForUser(userId);
    }

    @Transactional
    public RecordLoan checkoutRecord(RecordLoan loan) {
        Optional<Record> recordOpt = recordRepository.findById(loan.getRecord().getId());

        if (recordOpt.isPresent()) {
            Record record = recordOpt.get();

            if (record.getAvailableCopies() > 0) {
                // Зменшити кількість доступних копій
                record.setAvailableCopies(record.getAvailableCopies() - 1);
                recordRepository.save(record);

                // Встановити дату позичання на сьогодні, якщо не вказана
                if (loan.getLoanDate() == null) {
                    loan.setLoanDate(LocalDate.now());
                }

                // Встановити дату повернення за замовчуванням на 14 днів уперед, якщо не вказана
                if (loan.getDueDate() == null) {
                    loan.setDueDate(loan.getLoanDate().plusDays(14));
                }

                // За замовчуванням, позичання є активним (не повернуто)
                loan.setIsReturned(false);

                return recordLoanRepository.save(loan);
            }
        }

        return null; // Повернути null, якщо позичання не вдалося
    }

    @Transactional
    public RecordLoan returnRecord(Long loanId, String condition) {
        Optional<RecordLoan> loanOpt = recordLoanRepository.findById(loanId);

        if (loanOpt.isPresent()) {
            RecordLoan loan = loanOpt.get();

            if (!loan.getIsReturned()) {
                // Збільшити кількість доступних копій
                Record record = loan.getRecord();
                record.setAvailableCopies(record.getAvailableCopies() + 1);
                recordRepository.save(record);

                // Оновити інформацію про позичання
                loan.setIsReturned(true);
                loan.setReturnDate(LocalDate.now());
                loan.setConditionOnReturn(condition);

                // Розрахувати штраф, якщо платівка повернута із запізненням
                if (loan.getDueDate().isBefore(loan.getReturnDate())) {
                    long daysLate = ChronoUnit.DAYS.between(loan.getDueDate(), loan.getReturnDate());
                    loan.setLateFee(BigDecimal.valueOf(daysLate * 10.0)); // 10 грн за день запізнення
                }

                return recordLoanRepository.save(loan);
            }
        }

        return null; // Повернути null, якщо повернення не вдалося
    }

    public RecordLoan save(RecordLoan loan) {
        return recordLoanRepository.save(loan);
    }

    public RecordLoan update(Long id, RecordLoan loanDetails) {
        return recordLoanRepository.findById(id)
                .map(existingLoan -> {
                    existingLoan.setRecord(loanDetails.getRecord());
                    existingLoan.setUser(loanDetails.getUser());
                    existingLoan.setLoanDate(loanDetails.getLoanDate());
                    existingLoan.setDueDate(loanDetails.getDueDate());
                    existingLoan.setReturnDate(loanDetails.getReturnDate());
                    existingLoan.setIsReturned(loanDetails.getIsReturned());
                    existingLoan.setLateFee(loanDetails.getLateFee());
                    existingLoan.setConditionOnLoan(loanDetails.getConditionOnLoan());
                    existingLoan.setConditionOnReturn(loanDetails.getConditionOnReturn());
                    existingLoan.setNotes(loanDetails.getNotes());
                    return recordLoanRepository.save(existingLoan);
                })
                .orElse(null);
    }

    public boolean deleteById(Long id) {
        if (recordLoanRepository.existsById(id)) {
            recordLoanRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

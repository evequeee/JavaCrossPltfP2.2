package com.example.project22.repository;

import com.example.project22.model.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface LibraryUserRepository extends JpaRepository<LibraryUser, Long> {

    Optional<LibraryUser> findByEmailIgnoreCase(String email);

    List<LibraryUser> findByLastNameContainingIgnoreCase(String lastName);

    List<LibraryUser> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(
            String firstName, String lastName);

    List<LibraryUser> findByIsActive(Boolean isActive);

    List<LibraryUser> findByMembershipExpiresLessThan(LocalDate date);

    @Query("SELECT u FROM LibraryUser u JOIN u.loans l GROUP BY u ORDER BY COUNT(l) DESC")
    List<LibraryUser> findTopReaders();

    @Query("SELECT u FROM LibraryUser u JOIN u.loans l WHERE l.isReturned = false AND l.dueDate < :today")
    List<LibraryUser> findUsersWithOverdueBooks(@Param("today") LocalDate today);
}

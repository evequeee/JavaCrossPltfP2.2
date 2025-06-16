package com.example.project22.service;

import com.example.project22.model.LibraryUser;
import com.example.project22.repository.LibraryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryUserService {

    private final LibraryUserRepository userRepository;

    @Autowired
    public LibraryUserService(LibraryUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<LibraryUser> findAll() {
        return userRepository.findAll();
    }

    public Optional<LibraryUser> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<LibraryUser> findByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }

    public List<LibraryUser> findByLastName(String lastName) {
        return userRepository.findByLastNameContainingIgnoreCase(lastName);
    }

    public List<LibraryUser> findByName(String firstName, String lastName) {
        return userRepository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(firstName, lastName);
    }

    public List<LibraryUser> findByActiveStatus(boolean isActive) {
        return userRepository.findByIsActive(isActive);
    }

    public List<LibraryUser> findByMembershipExpiresBefore(LocalDate date) {
        return userRepository.findByMembershipExpiresLessThan(date);
    }

    public List<LibraryUser> findTopReaders() {
        return userRepository.findTopReaders();
    }

    public List<LibraryUser> findUsersWithOverdueBooks() {
        return userRepository.findUsersWithOverdueBooks(LocalDate.now());
    }

    @Transactional
    public LibraryUser save(LibraryUser user) {
        return userRepository.save(user);
    }

    @Transactional
    public LibraryUser update(Long id, LibraryUser userDetails) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setFirstName(userDetails.getFirstName());
                    existingUser.setLastName(userDetails.getLastName());
                    existingUser.setEmail(userDetails.getEmail());
                    existingUser.setPhone(userDetails.getPhone());
                    existingUser.setAddress(userDetails.getAddress());
                    existingUser.setDateOfBirth(userDetails.getDateOfBirth());
                    existingUser.setMembershipExpires(userDetails.getMembershipExpires());
                    existingUser.setIsActive(userDetails.getIsActive());
                    existingUser.setNotes(userDetails.getNotes());
                    return userRepository.save(existingUser);
                })
                .orElse(null);
    }

    @Transactional
    public boolean extendMembership(Long userId, LocalDate newExpiryDate) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setMembershipExpires(newExpiryDate);
                    userRepository.save(user);
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    public boolean activateUser(Long userId) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setIsActive(true);
                    userRepository.save(user);
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    public boolean deactivateUser(Long userId) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setIsActive(false);
                    userRepository.save(user);
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    public void delete(Long id) {
        userRepository.findById(id).ifPresent(userRepository::delete);
    }

    @Transactional
    public boolean exists(Long id) {
        return userRepository.existsById(id);
    }

    @Transactional
    public boolean existsByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email).isPresent();
    }
}

package com.example.project22.controller;

import com.example.project22.model.LibraryUser;
import com.example.project22.service.LibraryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class LibraryUserController {

    private final LibraryUserService userService;

    @Autowired
    public LibraryUserController(LibraryUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<LibraryUser> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibraryUser> getUserById(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<LibraryUser> getUserByEmail(@PathVariable String email) {
        return userService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/lastname")
    public List<LibraryUser> getUsersByLastName(@RequestParam String lastName) {
        return userService.findByLastName(lastName);
    }

    @GetMapping("/search/name")
    public List<LibraryUser> getUsersByName(
            @RequestParam String firstName,
            @RequestParam String lastName) {
        return userService.findByName(firstName, lastName);
    }

    @GetMapping("/active")
    public List<LibraryUser> getActiveUsers(@RequestParam(required = false) Boolean active) {
        return userService.findByActiveStatus(active != null ? active : true);
    }

    @GetMapping("/expiring-membership")
    public List<LibraryUser> getUsersWithExpiringMembership() {
        // Get users whose membership expires within 30 days
        return userService.findByMembershipExpiresBefore(LocalDate.now().plusDays(30));
    }

    @GetMapping("/top-readers")
    public List<LibraryUser> getTopReaders() {
        return userService.findTopReaders();
    }

    @GetMapping("/with-overdue-books")
    public List<LibraryUser> getUsersWithOverdueBooks() {
        return userService.findUsersWithOverdueBooks();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<LibraryUser> createUser(@RequestBody LibraryUser user) {
        if (userService.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibraryUser> updateUser(@PathVariable Long id, @RequestBody LibraryUser user) {
        LibraryUser updatedUser = userService.update(id, user);
        return updatedUser != null
                ? ResponseEntity.ok(updatedUser)
                : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/extend-membership")
    public ResponseEntity<Void> extendMembership(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate expiryDate) {
        boolean updated = userService.extendMembership(id, expiryDate);
        return updated
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activateUser(@PathVariable Long id) {
        boolean updated = userService.activateUser(id);
        return updated
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateUser(@PathVariable Long id) {
        boolean updated = userService.deactivateUser(id);
        return updated
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userService.exists(id)) {
            userService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

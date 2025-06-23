package ua.edu.chnu.springjpaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.chnu.springjpaproject.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
package com.example.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public User registerNewUser(UserRegistrationDto registrationDto) {
        // Check if username already exists
        if (userRepository.existsByUsername(registrationDto.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        
        // Create new user with encoded password
        User newUser = new User(
            registrationDto.getUsername(),
            passwordEncoder.encode(registrationDto.getPassword()),
            registrationDto.getRole()
        );
        
        // Save and return the new user
        return userRepository.save(newUser);
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }
}

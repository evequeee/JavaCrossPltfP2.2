package ua.edu.chnu.springjpaproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.edu.chnu.springjpaproject.repository.UserRepository;
import ua.edu.chnu.springjpaproject.user.User;

import java.util.Collections;
import java.util.logging.Logger;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = Logger.getLogger(UserDetailsServiceImpl.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Attempting to load user: " + username);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    logger.warning("User not found: " + username);
                    return new UsernameNotFoundException("Користувача з іменем " + username + " не знайдено");
                });

        logger.info("User found: " + username + " with role: " + user.getRole());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
        );
    }
}

package com.shopsphere.userservice.service;

import com.shopsphere.userservice.entity.User;
import com.shopsphere.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // 🔥 NEW METHOD
    public User createUserIfNotExists(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return userRepository.findByEmail(user.getEmail()).get();
        }

        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }
}

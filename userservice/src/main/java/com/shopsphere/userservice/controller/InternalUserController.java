package com.shopsphere.userservice.controller;

import com.shopsphere.userservice.dto.UserCreateRequest;
import com.shopsphere.userservice.entity.User;
import com.shopsphere.userservice.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/internal")
public class InternalUserController {

    private final UserRepository userRepository;

    public InternalUserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createUser(@RequestBody UserCreateRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.ok().build();
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        // ❌ DO NOT SET createdAt manually

        userRepository.save(user);

        return ResponseEntity.ok().build();
    }
}

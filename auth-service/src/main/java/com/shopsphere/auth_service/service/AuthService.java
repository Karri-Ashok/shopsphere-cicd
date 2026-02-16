package com.shopsphere.auth_service.service;

import com.shopsphere.auth_service.client.UserServiceClient;
import com.shopsphere.auth_service.dto.AuthResponse;
import com.shopsphere.auth_service.dto.LoginRequest;
import com.shopsphere.auth_service.dto.RegisterRequest;
import com.shopsphere.auth_service.dto.UserCreateRequest;
import com.shopsphere.auth_service.exception.InvalidCredentialsException;
import com.shopsphere.auth_service.exception.UserNotFoundException;
import com.shopsphere.auth_service.model.Role;
import com.shopsphere.auth_service.model.User;
import com.shopsphere.auth_service.repository.UserRepository;
import com.shopsphere.auth_service.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserServiceClient userServiceClient;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil,
                       UserServiceClient userServiceClient) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.userServiceClient = userServiceClient;
    }

    public void register(RegisterRequest request) {

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);

        UserCreateRequest userRequest = new UserCreateRequest();
        userRequest.setName(request.getName());
        userRequest.setEmail(request.getEmail());
        userRequest.setRole(Role.USER.name());

        userServiceClient.createUser(userRequest);
    }


    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getRole().name()
        );

        return new AuthResponse(token);
    }
}

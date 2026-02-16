package com.shopsphere.userservice.controller;

import com.shopsphere.userservice.entity.User;
import com.shopsphere.userservice.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public User getProfile(
            @RequestHeader("X-USER-EMAIL") String email) {

        return userService.getUserByEmail(email);
    }
}

package com.shopsphere.userservice.dto;

import lombok.Data;

@Data
public class UserCreateRequest {
    private String name;
    private String email;
    private String role;
}

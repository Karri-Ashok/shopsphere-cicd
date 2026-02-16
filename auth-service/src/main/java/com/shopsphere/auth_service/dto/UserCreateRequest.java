package com.shopsphere.auth_service.dto;

import lombok.Data;

@Data
public class UserCreateRequest {
    private String name;
    private String email;
    private String role;
}

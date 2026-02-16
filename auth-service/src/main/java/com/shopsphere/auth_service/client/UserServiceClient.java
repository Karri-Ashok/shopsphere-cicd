package com.shopsphere.auth_service.client;

import com.shopsphere.auth_service.dto.UserCreateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "USER-SERVICE")
public interface UserServiceClient {

    @PostMapping("/api/users/internal/create")
    void createUser(@RequestBody UserCreateRequest request);
}

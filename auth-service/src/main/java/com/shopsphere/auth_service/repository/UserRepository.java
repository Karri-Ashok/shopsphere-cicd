package com.shopsphere.auth_service.repository;

import java.util.Optional;
import java.util.UUID;

import com.shopsphere.auth_service.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, UUID> {
		Optional<User> findByEmail(String email);
}

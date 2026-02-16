package com.shopsphere.cartservice.repository;

import com.shopsphere.cartservice.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByUserEmail(String userEmail);

    @Modifying
    @Transactional
    void deleteByUserEmailAndProductId(String userEmail, Long productId);
    Optional<CartItem> findByUserEmailAndProductId(String userEmail, Long productId);

}

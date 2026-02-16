package com.shopsphere.cartservice.service;

import com.shopsphere.cartservice.dto.AddToCartRequest;
import com.shopsphere.cartservice.entity.CartItem;
import com.shopsphere.cartservice.repository.CartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void addToCart(String userEmail, AddToCartRequest request) {

        CartItem existing = cartRepository
                .findByUserEmailAndProductId(userEmail, request.getProductId())
                .orElse(null);

        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + request.getQuantity());
            cartRepository.save(existing);
        } else {
            CartItem item = CartItem.builder()
                    .userEmail(userEmail)
                    .productId(request.getProductId())
                    .quantity(request.getQuantity())
                    .build();

            cartRepository.save(item);
        }
    }


    public List<CartItem> getCart(String userEmail) {
        return cartRepository.findByUserEmail(userEmail);
    }

    @Transactional
    public void removeFromCart(String email, Long productId) {
        cartRepository.deleteByUserEmailAndProductId(email, productId);
    }
}

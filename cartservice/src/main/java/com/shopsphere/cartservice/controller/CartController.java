package com.shopsphere.cartservice.controller;

import com.shopsphere.cartservice.dto.AddToCartRequest;
import com.shopsphere.cartservice.entity.CartItem;
import com.shopsphere.cartservice.service.CartService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(
            @RequestHeader("X-USER-EMAIL") String email,
            @Valid @RequestBody AddToCartRequest request) {

        cartService.addToCart(email, request);
        return ResponseEntity.ok("Added to cart");
    }

    @GetMapping
    public ResponseEntity<List<CartItem>> getCart(
            @RequestHeader("X-USER-EMAIL") String email) {

        return ResponseEntity.ok(cartService.getCart(email));
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<Void> removeFromCart(
            @RequestHeader("X-USER-EMAIL") String email,
            @PathVariable Long productId
    ) {
        cartService.removeFromCart(email, productId);
        return ResponseEntity.noContent().build();
    }

}

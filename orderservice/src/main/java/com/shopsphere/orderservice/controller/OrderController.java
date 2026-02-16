package com.shopsphere.orderservice.controller;

import com.shopsphere.orderservice.dto.CreateOrderRequest;
import com.shopsphere.orderservice.entity.Order;
import com.shopsphere.orderservice.service.OrderService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public Order createOrder(
            @RequestHeader("X-USER-EMAIL") String email,
            @Valid @RequestBody CreateOrderRequest request
    ) {
        return orderService.createOrder(email, request);
    }

    @GetMapping
    public List<Order> getOrders(
            @RequestHeader("X-USER-EMAIL") String email
    ) {
        return orderService.getOrders(email);
    }
}

package com.shopsphere.orderservice.service;

import com.shopsphere.orderservice.client.PaymentServiceClient;
import com.shopsphere.orderservice.dto.CreateOrderRequest;
import com.shopsphere.orderservice.dto.PaymentRequest;
import com.shopsphere.orderservice.entity.Order;
import com.shopsphere.orderservice.entity.OrderItem;
import com.shopsphere.orderservice.repository.OrderRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final PaymentServiceClient paymentServiceClient;

    public OrderService(OrderRepository orderRepository,
                        PaymentServiceClient paymentServiceClient) {
        this.orderRepository = orderRepository;
        this.paymentServiceClient = paymentServiceClient;
    }

    @Transactional
    public Order createOrder(String userEmail, CreateOrderRequest request) {

        List<OrderItem> items = request.getItems().stream()
                .map(i -> OrderItem.builder()
                        .productId(i.getProductId())
                        .quantity(i.getQuantity())
                        .price(i.getPrice()) 
                        .build())
                .toList();

        BigDecimal total = items.stream()
                .map(i -> i.getPrice()
                        .multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 🔥 Call Payment Service
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setUserEmail(userEmail);
        paymentRequest.setAmount(total);

        paymentServiceClient.processPayment(userEmail, paymentRequest);

        Order order = Order.builder()
                .userEmail(userEmail)
                .totalAmount(total)
                .items(items)
                .build();

        items.forEach(i -> i.setOrder(order));

        return orderRepository.save(order);
    }

    public List<Order> getOrders(String userEmail) {
        return orderRepository.findByUserEmail(userEmail);
    }
}

package com.shopsphere.paymentservice.controller;

import com.shopsphere.paymentservice.dto.PaymentRequest;
import com.shopsphere.paymentservice.entity.Payment;
import com.shopsphere.paymentservice.service.PaymentService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/pay")
    public Payment makePayment(
            @RequestHeader("X-USER-EMAIL") String email,
            @Valid @RequestBody PaymentRequest request
    ) {
        return paymentService.makePayment(email, request);
    }

    @GetMapping
    public List<Payment> getPayments(
            @RequestHeader("X-USER-EMAIL") String email
    ) {
        return paymentService.getPayments(email);
    }
}

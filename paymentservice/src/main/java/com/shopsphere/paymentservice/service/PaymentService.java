package com.shopsphere.paymentservice.service;

import com.shopsphere.paymentservice.dto.PaymentRequest;
import com.shopsphere.paymentservice.entity.Payment;
import com.shopsphere.paymentservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment makePayment(String email, PaymentRequest request) {

        Payment payment = Payment.builder()
                .orderId(request.getOrderId())
                .userEmail(email)
                .amount(request.getAmount())
                .status("SUCCESS")
                .createdAt(LocalDateTime.now())
                .build();

        return paymentRepository.save(payment);
    }

    public List<Payment> getPayments(String email) {
        return paymentRepository.findByUserEmail(email);
    }
}

package com.shopsphere.orderservice.client;

import com.shopsphere.orderservice.dto.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "PAYMENT-SERVICE")
public interface PaymentServiceClient {

    @PostMapping("/api/payments/pay")
    Object processPayment(
            @RequestHeader("X-USER-EMAIL") String email,
            @RequestBody PaymentRequest request
    );
}

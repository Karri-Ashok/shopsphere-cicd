package com.shopsphere.orderservice.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PaymentRequest {
    private String userEmail;
    private BigDecimal amount;
}

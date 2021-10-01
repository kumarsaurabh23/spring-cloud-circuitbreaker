package com.tutorial.os.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Payment {
    private String paymentStatus;
    private String transactionId;
    private int orderId;
    private Double amount;
}

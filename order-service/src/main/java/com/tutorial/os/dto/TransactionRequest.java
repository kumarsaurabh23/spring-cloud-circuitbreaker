package com.tutorial.os.dto;

import com.tutorial.os.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionRequest {

    private Order order;
    private Payment payment;
}

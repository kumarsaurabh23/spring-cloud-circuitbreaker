package com.tutorial.ps.service;

import com.tutorial.ps.entity.Payment;
import com.tutorial.ps.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment doPayment(Payment payment) {
        payment.setPaymentStatus(getPaymentProcessingStatus());
        payment.setTransactionId(UUID.randomUUID().toString());
        return paymentRepository.save(payment);
    }

    private String getPaymentProcessingStatus() {
        return new Random().nextBoolean() ? "success" : "false";
    }
}

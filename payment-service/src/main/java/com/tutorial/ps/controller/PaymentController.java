package com.tutorial.ps.controller;

import com.tutorial.ps.entity.Payment;
import com.tutorial.ps.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping(path = "/doPayment", produces = MediaType.APPLICATION_JSON_VALUE)
    public Payment doPayment(@RequestBody Payment payment) {
        return paymentService.doPayment(payment);
    }

    @PostMapping(path = "/doDelayedPayment", produces = MediaType.APPLICATION_JSON_VALUE)
    public Payment doDelayedPayment(@RequestBody Payment payment) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // DO NOTHING
        }
        return paymentService.doPayment(payment);
    }
}

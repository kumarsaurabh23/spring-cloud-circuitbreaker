package com.tutorial.os.service;

import com.tutorial.os.dto.Payment;
import com.tutorial.os.dto.TransactionRequest;
import com.tutorial.os.dto.TransactionResponse;
import com.tutorial.os.entity.Order;
import com.tutorial.os.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RestTemplate restTemplate;
    private static final String PAYMENT_SERVICE_URL = "http://localhost:9092/payment/doPayment";

    @CircuitBreaker(name="paymentCB", fallbackMethod = "fallbackOrder")
    public TransactionResponse saveOrder(TransactionRequest request) {
        String message = "";
        Payment payment = request.getPayment();
        payment.setOrderId(request.getOrder().getId());
        payment.setAmount(request.getOrder().getPrice());
        // rest call to payment service
        Payment paymentResponse = restTemplate.postForObject(PAYMENT_SERVICE_URL, payment, Payment.class);
        Order order = request.getOrder();
        if("success".equals(paymentResponse.getPaymentStatus())) {
            orderRepository.save(order);
            message = "order placed successfully";
        } else {
            message = "error while placing order";
        }
        return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(), message);
    }

    public Order findOrderDetailsById(int orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    private TransactionResponse fallbackOrder(Throwable th) {
        String message = "payment service is having issues";
        return new TransactionResponse(null, null, null, message);
    }
}

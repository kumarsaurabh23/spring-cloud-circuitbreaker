package com.tutorial.os.controller;

import com.tutorial.os.dto.TransactionRequest;
import com.tutorial.os.dto.TransactionResponse;
import com.tutorial.os.entity.Order;
import com.tutorial.os.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(path = "/bookOrder", produces = MediaType.APPLICATION_JSON_VALUE)
    public TransactionResponse bookOrder(@RequestBody TransactionRequest request) {
        return orderService.saveOrder(request);
    }

    @GetMapping(path = "/getOrderDetails/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Order getOrderDetails(@PathVariable int orderId) {
        return orderService.findOrderDetailsById(orderId);
    }
}

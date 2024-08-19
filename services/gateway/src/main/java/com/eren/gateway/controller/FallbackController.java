package com.eren.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
public class FallbackController {

    @GetMapping("/fallback/customers")
    public ResponseEntity<String> customerServiceFallback() {
        return ResponseEntity.ok("Customer Service is currently unavailable. Please try again later.");
    }

    @GetMapping("/fallback/orders")
    public ResponseEntity<String> orderServiceFallback() {
        return ResponseEntity.ok("Order Service is currently unavailable. Please try again later.");
    }

    @GetMapping("/fallback/order-lines")
    public ResponseEntity<String> orderLinesServiceFallback() {
        return ResponseEntity.ok("Order Lines Service is currently unavailable. Please try again later.");
    }

    @GetMapping("/fallback/payments")
    public ResponseEntity<String> paymentServiceFallback() {
        return ResponseEntity.ok("Payment Service is currently unavailable. Please try again later.");
    }

    @GetMapping("/fallback/products")
    public ResponseEntity<String> productServiceFallback() {
        return ResponseEntity.ok("Product Service is currently unavailable. Please try again later.");
    }
}
package com.eren.ecommerce.payment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;

    // HTTP POST isteği ile ödeme oluşturur.
    @PostMapping
    public ResponseEntity<Integer> createPayment(
            // `PaymentRequest` nesnesinin geçerli olduğundan emin olmak için `@Valid` anotasyonu kullanılır.
            @RequestBody @Valid PaymentRequest request
    ) {
        // Ödeme oluşturma işlemini `PaymentService` aracılığıyla yapar ve sonucu HTTP 200 OK ile döner.
        return ResponseEntity.ok(this.service.createPayment(request));
    }
}
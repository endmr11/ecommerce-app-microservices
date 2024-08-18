package com.eren.ecommerce.payment;

import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

    // `PaymentRequest` nesnesini `Payment` nesnesine dönüştüren metod
    public Payment toPayment(PaymentRequest request) {
        // Eğer `request` null ise, null döner.
        if (request == null) {
            return null;
        }
        // `Payment` nesnesini `PaymentRequest` nesnesine göre oluşturur.
        return Payment.builder()
                .id(request.id()) // Ödeme ID'sini ayarlar.
                .paymentMethod(request.paymentMethod()) // Ödeme yöntemini ayarlar.
                .amount(request.amount()) // Ödeme miktarını ayarlar.
                .orderId(request.orderId()) // Sipariş ID'sini ayarlar.
                .build(); // `Payment` nesnesini oluşturur.
    }
}
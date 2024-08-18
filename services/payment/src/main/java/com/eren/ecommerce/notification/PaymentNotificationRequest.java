package com.eren.ecommerce.notification;

import com.eren.ecommerce.payment.PaymentMethod;

import java.math.BigDecimal;

// `PaymentNotificationRequest` sınıfı, ödeme bildirimleri için gerekli bilgileri içeren bir veri taşıyıcıdır.
// Bu sınıf, Java'nın `record` özelliğini kullanır ve immutability (değişmezlik) sağlar.
public record PaymentNotificationRequest(
        String orderReference,         // Siparişin referans numarası
        BigDecimal amount,             // Ödeme tutarı
        PaymentMethod paymentMethod,   // Ödeme yöntemi (örneğin kredi kartı, banka transferi vb.)
        String customerFirstname,      // Müşterinin adı
        String customerLastname,       // Müşterinin soyadı
        String customerEmail           // Müşterinin e-posta adresi
) {
}
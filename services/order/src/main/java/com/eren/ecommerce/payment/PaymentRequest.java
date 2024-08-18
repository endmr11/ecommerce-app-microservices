package com.eren.ecommerce.payment;

import com.eren.ecommerce.customer.CustomerResponse;
import com.eren.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
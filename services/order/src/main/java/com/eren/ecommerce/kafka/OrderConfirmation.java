package com.eren.ecommerce.kafka;

import com.eren.ecommerce.customer.CustomerResponse;
import com.eren.ecommerce.order.PaymentMethod;
import com.eren.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation (
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products

) {
}

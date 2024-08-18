package com.eren.ecommerce.orderline;

public record OrderLineResponse(
        Integer id,
        double quantity
) { }
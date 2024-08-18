package com.eren.ecommerce.customer;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
@Validated
public class Address {
    private String street;
    private String houseNumber;
    private String zipCode;
}

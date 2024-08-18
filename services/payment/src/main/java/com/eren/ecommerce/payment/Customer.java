package com.eren.ecommerce.payment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

// `Customer` sınıfı, müşteri bilgilerini temsil eder ve Spring'in doğrulama mekanizmalarını kullanır.
// `@Validated` anotasyonu, sınıfın içindeki doğrulama kurallarının uygulanmasını sağlar.
@Validated
public record Customer(
        String id,                             // Müşterinin benzersiz kimlik numarası
        @NotNull(message = "Firstname is required")   // Adın zorunlu olduğunu belirten doğrulama kuralı
        String firstName,                     // Müşterinin adı
        @NotNull(message = "Lastname is required")    // Soyadın zorunlu olduğunu belirten doğrulama kuralı
        String lastName,                      // Müşterinin soyadı
        @NotNull(message = "Email is required")       // E-postanın zorunlu olduğunu belirten doğrulama kuralı
        @Email(message = "The customer email is not correctly formatted")  // E-posta formatının doğruluğunu kontrol eden kural
        String email                         // Müşterinin e-posta adresi
) { }
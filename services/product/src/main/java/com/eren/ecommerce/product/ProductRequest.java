package com.eren.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

// `ProductRequest`, ürün bilgilerini taşımak için kullanılan bir veri yapısıdır.
// `record` anahtar kelimesi, bu veri yapısının bir kayıt olduğunu belirtir.
// Kayıtlar, veriyi taşımak için kullanılan immütabl (değiştirilemez) nesnelerdir.
public record ProductRequest(
        // `id` alanı, ürünün benzersiz kimliğini belirtir.
        // Bu alan, ürün oluşturulurken sağlanması gerekmez, çünkü yeni ürünler için id otomatik olarak atanır.
        Integer id,

        // `name` alanı, ürünün adını belirtir.
        // `@NotNull` annotasyonu, bu alanın boş olamayacağını belirtir.
        @NotNull(message = "Product name is required")
        String name,

        // `description` alanı, ürünün açıklamasını belirtir.
        // `@NotNull` annotasyonu, bu alanın boş olamayacağını belirtir.
        @NotNull(message = "Product description is required")
        String description,

        // `availableQuantity` alanı, ürünün mevcut miktarını belirtir.
        // `@Positive` annotasyonu, bu alanın sıfırdan büyük olması gerektiğini belirtir.
        @Positive(message = "Available quantity should be positive")
        double availableQuantity,

        // `price` alanı, ürünün fiyatını belirtir.
        // `@Positive` annotasyonu, bu alanın sıfırdan büyük olması gerektiğini belirtir.
        @Positive(message = "Price should be positive")
        BigDecimal price,

        // `categoryId` alanı, ürünün ait olduğu kategorinin kimliğini belirtir.
        // `@NotNull` annotasyonu, bu alanın boş olamayacağını belirtir.
        @NotNull(message = "Product category is required")
        Integer categoryId
) {
}
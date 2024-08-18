package com.eren.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

// `ProductPurchaseRequest` sınıfı, ürün satın alma isteklerini temsil eden bir veri taşıyıcı (DTO) olarak kullanılır.
// `record` anahtar kelimesi, bu sınıfın veri taşıyıcı bir sınıf olduğunu ve otomatik olarak getter'lar,
// equals(), hashCode(), ve toString() metodlarını sağladığını belirtir.
public record ProductPurchaseRequest(

        // `@NotNull` anotasyonu, bu alanın null olamayacağını belirtir.
        // `message` parametresi, doğrulama hatası durumunda gösterilecek hata mesajını belirtir.
        @NotNull(message = "Product is mandatory")
        Integer productId,

        // `@Positive` anotasyonu, bu alanın sıfırdan büyük bir değere sahip olması gerektiğini belirtir.
        // `message` parametresi, doğrulama hatası durumunda gösterilecek hata mesajını belirtir.
        @Positive(message = "Quantity is mandatory")
        double quantity
) {
}
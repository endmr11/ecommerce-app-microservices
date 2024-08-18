package com.eren.ecommerce.product;

import java.math.BigDecimal;

// `ProductPurchaseResponse` sınıfı, ürün satın alma yanıtlarını temsil eden bir veri taşıyıcı (DTO) olarak kullanılır.
// `record` anahtar kelimesi, bu sınıfın veri taşıyıcı bir sınıf olduğunu ve otomatik olarak getter'lar,
// equals(), hashCode(), ve toString() metodlarını sağladığını belirtir.
public record ProductPurchaseResponse(

        // Ürünün kimliğini temsil eder. Bu alan `Integer` türündedir ve ürünün benzersiz kimliğini içerir.
        Integer productId,

        // Ürünün adını temsil eder. Bu alan `String` türündedir ve ürünün ismini içerir.
        String name,

        // Ürünün açıklamasını temsil eder. Bu alan `String` türündedir ve ürün hakkında detaylı bilgi sağlar.
        String description,

        // Ürünün fiyatını temsil eder. Bu alan `BigDecimal` türündedir ve yüksek hassasiyete sahip fiyat bilgisi sağlar.
        BigDecimal price,

        // Satın alınan ürün miktarını temsil eder. Bu alan `double` türündedir ve ürünün satın alınan miktarını içerir.
        double quantity
) {
}
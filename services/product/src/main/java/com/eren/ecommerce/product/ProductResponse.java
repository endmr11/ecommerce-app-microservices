package com.eren.ecommerce.product;

import java.math.BigDecimal;

// `ProductResponse`, ürün bilgilerini yanıt olarak taşımak için kullanılan bir veri yapısıdır.
// `record` anahtar kelimesi, bu veri yapısının bir kayıt olduğunu belirtir.
// Kayıtlar, veriyi taşımak için kullanılan immütabl (değiştirilemez) nesnelerdir.
public record ProductResponse(
        // `id` alanı, ürünün benzersiz kimliğini belirtir.
        // Bu alan, ürünü tanımlamak için kullanılır.
        Integer id,

        // `name` alanı, ürünün adını belirtir.
        // Bu, ürünün kullanıcıya gösterilen adıdır.
        String name,

        // `description` alanı, ürünün açıklamasını belirtir.
        // Bu alan, ürünün detaylı açıklamasını içerir.
        String description,

        // `availableQuantity` alanı, ürünün mevcut miktarını belirtir.
        // Bu, stokta bulunan ürün sayısını gösterir.
        double availableQuantity,

        // `price` alanı, ürünün fiyatını belirtir.
        // `BigDecimal` kullanılarak fiyatın hassas bir şekilde saklanması sağlanır.
        BigDecimal price,

        // `categoryId` alanı, ürünün ait olduğu kategorinin kimliğini belirtir.
        // Bu alan, ürünün hangi kategoriye ait olduğunu tanımlar.
        Integer categoryId,

        // `categoryName` alanı, ürünün ait olduğu kategorinin adını belirtir.
        // Bu, kullanıcıya kategori adı ile bilgi vermek için kullanılır.
        String categoryName,

        // `categoryDescription` alanı, ürünün ait olduğu kategorinin açıklamasını belirtir.
        // Bu, kategori hakkında daha fazla bilgi sağlamak için kullanılır.
        String categoryDescription
) {
}
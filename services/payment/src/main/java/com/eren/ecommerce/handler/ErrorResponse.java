package com.eren.ecommerce.handler;

import java.util.Map;

// `ErrorResponse` sınıfı, hata yanıtlarını temsil etmek için kullanılır.
// `record` anahtar kelimesi, bu sınıfın bir kayıt (immutable) sınıf olduğunu belirtir.
public record ErrorResponse(
        Map<String, String> errors // Hataların bir harita (map) olarak saklandığı alandır.
) {

}
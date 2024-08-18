package com.eren.ecommerce.handler;

import java.util.Map;

// `ErrorResponse` sınıfı, hata yanıtlarını temsil eden bir kayıt (record) sınıfıdır.
// Bu sınıf, hata bilgilerini bir `Map` olarak tutar.
public record ErrorResponse(
        Map<String, String> errors // Hataların anahtar-değer çiftlerini içeren bir harita (map) olarak tanımlanır.
) {

}
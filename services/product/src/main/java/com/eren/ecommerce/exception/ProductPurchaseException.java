package com.eren.ecommerce.exception;

// `ProductPurchaseException` sınıfı `RuntimeException` sınıfından türetilmiştir ve ürün satın alma hatalarını temsil eder.
public class ProductPurchaseException extends RuntimeException {

    // Yapıcı metod, hata mesajını parametre olarak alır ve üst sınıfın yapıcısına (superclass constructor) iletir.
    public ProductPurchaseException(String s) {
        super(s); // `RuntimeException` sınıfının yapıcısına hata mesajını iletir.
    }
}
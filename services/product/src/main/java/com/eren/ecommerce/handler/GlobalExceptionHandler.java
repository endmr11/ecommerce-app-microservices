package com.eren.ecommerce.handler;

// Gerekli importlar
import com.eren.ecommerce.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

// `GlobalExceptionHandler` sınıfı, uygulamanın genel hata işleme mantığını merkezi olarak yönetir.
// `@RestControllerAdvice` anotasyonu, bu sınıfın tüm REST denetleyicileri için global hata işleyici olduğunu belirtir.
@RestControllerAdvice
public class GlobalExceptionHandler {

    // `ProductPurchaseException` türündeki istisnaları yakalar ve işleme alır.
    @ExceptionHandler(ProductPurchaseException.class)
    public ResponseEntity<String> handle(ProductPurchaseException exp) {
        // İstisna durumunda HTTP 400 Bad Request yanıtı döner ve istisna mesajı yanıt gövdesinde iletilir.
        return ResponseEntity
                .status(BAD_REQUEST) // HTTP durum kodunu 400 Bad Request olarak ayarlar.
                .body(exp.getMessage()); // İstisna mesajını yanıt gövdesine ekler.
    }

    // `EntityNotFoundException` türündeki istisnaları yakalar ve işleme alır.
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handle(EntityNotFoundException exp) {
        // İstisna durumunda HTTP 400 Bad Request yanıtı döner ve istisna mesajı yanıt gövdesinde iletilir.
        return ResponseEntity
                .status(BAD_REQUEST) // HTTP durum kodunu 400 Bad Request olarak ayarlar.
                .body(exp.getMessage()); // İstisna mesajını yanıt gövdesine ekler.
    }

    // `MethodArgumentNotValidException` türündeki istisnaları yakalar ve işleme alır.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp) {
        // Hatalı alanlar ve mesajlarını saklayacak bir HashMap oluşturur.
        var errors = new HashMap<String, String>();

        // Tüm doğrulama hatalarını alır ve işleme alır.
        exp.getBindingResult().getAllErrors()
                .forEach(error -> {
                    // Hata mesajı ve alan adını alır.
                    var fieldName = ((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();

                    // Alan adı ve hata mesajını map'e ekler.
                    errors.put(fieldName, errorMessage);
                });

        // HTTP 400 Bad Request yanıtı döner ve `ErrorResponse` nesnesini yanıt gövdesinde iletilir.
        return ResponseEntity
                .status(BAD_REQUEST) // HTTP durum kodunu 400 Bad Request olarak ayarlar.
                .body(new ErrorResponse(errors)); // `ErrorResponse` nesnesini yanıt gövdesine ekler.
    }
}
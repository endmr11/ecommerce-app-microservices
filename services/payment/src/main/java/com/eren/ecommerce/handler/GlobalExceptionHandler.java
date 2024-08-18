package com.eren.ecommerce.handler;

import com.eren.ecommerce.exception.BusinessException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

// `@RestControllerAdvice` anotasyonu, uygulama genelindeki hata yönetimini merkezi bir yerde toplamak için kullanılır.
// `@RestControllerAdvice` sınıfı, hata işleme için kullanılan metodları içerir ve tüm REST denetleyicileri tarafından yakalanan istisnaları ele alır.
@RestControllerAdvice
public class GlobalExceptionHandler {

    // `@ExceptionHandler` anotasyonu, belirli bir istisna türü için özel bir hata işleme metodunu işaretler.
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handle(EntityNotFoundException exp) {
        // `EntityNotFoundException` istisnası oluştuğunda bu metod çağrılır.
        // Yanıtın HTTP durum kodunu NOT_FOUND (404) olarak ayarlar ve istisna mesajını gövde olarak döner.
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exp.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp) {
        // `MethodArgumentNotValidException` istisnası oluştuğunda bu metod çağrılır.
        // Bu istisna genellikle doğrulama hataları ile ilgilidir.
        var errors = new HashMap<String, String>();
        // `BindingResult` içinde bulunan tüm hataları alır ve bir `HashMap` içine yerleştirir.
        exp.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var fieldName = ((FieldError) error).getField(); // Hata alanının adını alır.
                    var errorMessage = error.getDefaultMessage(); // Hata mesajını alır.
                    errors.put(fieldName, errorMessage); // Alan adı ve hata mesajını `HashMap`'e ekler.
                });

        // HTTP durum kodunu BAD_REQUEST (400) olarak ayarlar ve `ErrorResponse` objesini yanıt gövdesi olarak döner.
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new ErrorResponse(errors));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handle(BusinessException exp) {
        // `BusinessException` istisnası oluştuğunda bu metod çağrılır.
        // Yanıtın HTTP durum kodunu BAD_REQUEST (400) olarak ayarlar ve `BusinessException`'ın mesajını gövde olarak döner.
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exp.getMsg());
    }
}
package com.eren.ecommerce.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

// `BusinessException` sınıfı, `RuntimeException` sınıfından türemektedir.
// Bu, iş mantığı hatalarını temsil eden özel bir exception sınıfıdır.
@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {

    // `msg` alanı, hata mesajını depolamak için kullanılır.
    private final String msg;

    // `BusinessException` sınıfı, `RuntimeException` sınıfının yapılandırıcısını çağırarak oluşturulur.
    // `msg` alanı, hata mesajını belirtir.
}
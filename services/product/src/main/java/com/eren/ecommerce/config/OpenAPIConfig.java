package com.eren.ecommerce.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Bu anotasyon, sınıfın bir Spring konfigürasyon sınıfı olduğunu belirtir.
public class OpenAPIConfig {

    @Bean // Bu metod, Spring konteynerine OpenAPI nesnesini bir Bean olarak ekler.
    public OpenAPI productServiceAPI() {
        // OpenAPI nesnesi oluşturulur ve API ile ilgili meta bilgiler eklenir.
        return new OpenAPI()
                .info(new Info()
                        .title("Product Service API") // API başlığı.
                        .description("Product Service API for managing products") // API açıklaması.
                        .version("v1") // API sürüm numarası.
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")) // Lisans bilgisi ve lisansın URL'si.
                )
                .externalDocs(new ExternalDocumentation()
                        .description("SpringDoc OpenAPI 3.0") // Dış dokümantasyonun açıklaması.
                );
    }
}
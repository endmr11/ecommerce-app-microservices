package com.eren.configserver; // Paket tanımı: com.eren.configserver paketi altında bu sınıf bulunur.

import org.springframework.boot.SpringApplication; // SpringApplication sınıfını içe aktarır. Spring Boot uygulamasını başlatmak için kullanılır.
import org.springframework.boot.autoconfigure.SpringBootApplication; // SpringBootApplication anotasyonunu içe aktarır. Spring Boot konfigürasyonlarını sağlar.
import org.springframework.cloud.config.server.EnableConfigServer; // EnableConfigServer anotasyonunu içe aktarır. Konfigürasyon sunucusu işlevselliğini etkinleştirir.

@SpringBootApplication // Spring Boot uygulamasını başlatmak için gerekli konfigürasyonları sağlar.
@EnableConfigServer // Bu uygulamanın bir konfigürasyon sunucusu olarak çalışmasını sağlar.
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args); // Spring Boot uygulamasını başlatır.
		// ConfigServerApplication sınıfını ana uygulama sınıfı olarak kullanır.
	}
}
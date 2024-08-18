package com.eren.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// JPA denetimini etkinleştirir.
// Bu, `@CreatedDate`, `@LastModifiedDate` gibi anotasyonlarla otomatik zaman damgalarını eklemeye olanak tanır.
// Bu, veritabanı işlemleri sırasında kayıtların ne zaman oluşturulduğunu veya güncellendiğini takip eder.
@EnableJpaAuditing

// Spring Boot uygulamasını başlatmak için kullanılan ana sınıf.
// `@SpringBootApplication` anotasyonu, uygulamanın başlangıç sınıfını belirtir ve aşağıdaki bileşenleri içerir:
// - `@Configuration`: Bu sınıfın bir Spring yapılandırma sınıfı olduğunu belirtir.
// - `@EnableAutoConfiguration`: Spring Boot'un uygulamanın yapılandırmasını otomatik olarak yapmasını sağlar.
// - `@ComponentScan`: Spring'in bu paketteki bileşenleri, servisleri ve diğer Spring bileşenlerini taramasını sağlar.
@SpringBootApplication
public class PaymentApplication {

	// Uygulamanın başlatıldığı ana yöntem.
	public static void main(String[] args) {
		// Spring Boot uygulamasını başlatır ve bağlamı (context) oluşturur.
		SpringApplication.run(PaymentApplication.class, args);
	}

}
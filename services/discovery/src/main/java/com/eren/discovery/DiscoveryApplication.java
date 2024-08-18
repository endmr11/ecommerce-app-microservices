package com.eren.discovery;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
// Bu notasyon, bu uygulamanın bir Eureka servis kayıt sunucusu olduğunu belirtir.
// Mikro hizmetlerin kendilerini kayıt ettikleri ve birbirleriyle iletişim kurdukları bir merkezi sunucu sağlar.

public class DiscoveryApplication {

	public static void main(String[] args) {
		// Uygulamanın ana metodudur, Spring Boot uygulamasını başlatmak için kullanılır.
		SpringApplication.run(DiscoveryApplication.class, args);
		// `DiscoveryApplication` sınıfını başlatır ve tüm yapılandırmaları yükler.
	}

}
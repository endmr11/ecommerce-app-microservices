package com.eren.ecommerce.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaPaymentTopicConfig {

    // Spring konteyneri tarafından yönetilen bir `NewTopic` bean'i tanımlar.
    // Bu, Kafka'da bir konu (topic) oluşturur.
    @Bean
    public NewTopic paymentTopic() {
        // `TopicBuilder` sınıfını kullanarak yeni bir Kafka konusunun yapılandırmasını oluşturur.
        return TopicBuilder
                .name("payment-topic") // Konunun adını belirler.
                .build(); // Konuyu oluşturur ve döndürür.
    }
}
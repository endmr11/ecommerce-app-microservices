package com.eren.ecommerce.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {

    // KafkaTemplate, Kafka'ya mesaj göndermek için kullanılan bir araçtır. Bu örnekte, `String` anahtar ve `PaymentNotificationRequest` değer türünü kullanır.
    private final KafkaTemplate<String, PaymentNotificationRequest> kafkaTemplate;

    // `sendNotification` metodu, bir `PaymentNotificationRequest` nesnesini alır ve Kafka'ya bir mesaj olarak gönderir.
    public void sendNotification(PaymentNotificationRequest request) {
        // Loglama yaparak gönderilen mesajın içeriğini bilgi olarak kaydeder.
        log.info("Sending notification with body = < {} >", request);

        // Kafka'ya gönderilecek mesajı yapılandırır.
        // `MessageBuilder` kullanarak mesajın içeriğini (`request`) ve başlıklarını ayarlar.
        Message<PaymentNotificationRequest> message = MessageBuilder
                .withPayload(request) // Mesajın içeriğini belirler.
                .setHeader(TOPIC, "payment-topic") // Mesaj başlığına Kafka konusu adını (`payment-topic`) ekler.
                .build(); // Mesajı oluşturur.

        // Yapılandırılan mesajı Kafka'ya gönderir.
        kafkaTemplate.send(message);
    }
}
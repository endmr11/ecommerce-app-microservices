package com.eren.ecommerce.payment;

import com.eren.ecommerce.notification.NotificationProducer;
import com.eren.ecommerce.notification.PaymentNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repository; // Ödemeler için veri erişim katmanı
    private final PaymentMapper mapper; // `PaymentRequest` ve `Payment` arasında dönüşüm yapar
    private final NotificationProducer notificationProducer; // Ödeme bildirimlerini gönderir

    // Ödeme işlemi oluşturur ve bildirimi gönderir
    public Integer createPayment(PaymentRequest request) {
        // `PaymentRequest` nesnesini `Payment` nesnesine dönüştürür ve veritabanına kaydeder
        var payment = this.repository.save(this.mapper.toPayment(request));

        // Ödeme bilgilerini içeren bir bildirim oluşturur ve gönderir
        this.notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.orderReference(), // Sipariş referansı
                        request.amount(), // Ödeme miktarı
                        request.paymentMethod(), // Ödeme yöntemi
                        request.customer().firstName(), // Müşteri adı
                        request.customer().lastName(), // Müşteri soyadı
                        request.customer().email() // Müşteri e-posta adresi
                )
        );
        // Oluşturulan ödeme nesnesinin ID'sini döner
        return payment.getId();
    }
}
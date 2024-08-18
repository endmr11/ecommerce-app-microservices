package com.eren.ecommerce.payment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

// `@Entity` anotasyonu, bu sınıfın bir JPA varlık sınıfı olduğunu belirtir.
// `@Table` anotasyonu, varlığın veritabanında hangi tabloya karşılık geldiğini belirtir.
@Entity
@Table(name = "payment")
@EntityListeners(AuditingEntityListener.class) // JPA'nın otomatik zaman damgalarını yönetmek için kullanılan bir dinleyici sınıfı
@NoArgsConstructor // Parametresiz bir yapıcı (constructor) oluşturur.
@AllArgsConstructor // Tüm alanları parametre olarak alan bir yapıcı oluşturur.
@Builder // Bir `Builder` sınıfı oluşturur, bu da nesnelerin oluşturulmasını daha kolay hale getirir.
@Getter // Getter metodlarını otomatik olarak oluşturur.
@Setter // Setter metodlarını otomatik olarak oluşturur.
public class Payment {

    // `@Id` anotasyonu, bu alanın varlığın birincil anahtar olduğunu belirtir.
    // `@GeneratedValue` anotasyonu, bu alanın otomatik olarak artan bir değer alacağını belirtir.
    @Id
    @GeneratedValue
    private Integer id;

    // Ödemenin miktarını temsil eden bir alan.
    private BigDecimal amount;

    // Ödemenin yöntemini temsil eder. `@Enumerated(EnumType.STRING)` anotasyonu, enum değerlerinin veritabanında string olarak saklanmasını sağlar.
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    // İlgili siparişin ID'sini temsil eden bir alan.
    private Integer orderId;

    // Bu alan, varlık oluşturulduğunda otomatik olarak doldurulur ve güncellenemez.
    // `@CreatedDate` anotasyonu, varlık oluşturulma zamanını otomatik olarak ayarlar.
    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdDate;

    // Bu alan, varlık her güncellendiğinde otomatik olarak güncellenir.
    // `@LastModifiedDate` anotasyonu, varlık son güncellenme zamanını otomatik olarak ayarlar.
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;
}
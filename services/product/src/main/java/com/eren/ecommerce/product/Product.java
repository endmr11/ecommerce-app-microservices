package com.eren.ecommerce.product;

// Gerekli importlar
import com.eren.ecommerce.category.Category;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

// `@AllArgsConstructor` annotasyonu, tüm alanları parametre olarak alan bir yapıcı (constructor) oluşturur.
// `@NoArgsConstructor` annotasyonu, parametresiz bir yapıcı (constructor) oluşturur.
// `@Builder` annotasyonu, builder deseni ile nesne oluşturmayı sağlar.
// `@Getter` annotasyonu, tüm alanlar için getter metodlarını otomatik olarak oluşturur.
// `@Setter` annotasyonu, tüm alanlar için setter metodlarını otomatik olarak oluşturur.
// `@Entity` annotasyonu, bu sınıfın bir JPA varlık (entity) sınıfı olduğunu belirtir.
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Product {

    // `@Id` annotasyonu, bu alanın birincil anahtar olduğunu belirtir.
    // `@GeneratedValue` annotasyonu, bu alanın otomatik olarak değer üretmesini sağlar.
    @Id
    @GeneratedValue
    private Integer id;

    // Ürünün adı
    private String name;

    // Ürünün açıklaması
    private String description;

    // Mevcut miktar
    private double availableQuantity;

    // Ürünün fiyatı
    // `BigDecimal` kullanımı, hassas finansal hesaplamalar için uygundur.
    private BigDecimal price;

    // `@ManyToOne` annotasyonu, bu alanın birçok ürünün bir kategoriye ait olduğunu belirtir.
    // `@JoinColumn` annotasyonu, bu ilişki için kullanılan yabancı anahtar sütununu belirtir.
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
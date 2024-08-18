package com.eren.ecommerce.category;

// `Product` sinifi, bu sinifta kullanilan `products` listesi icin import ediliyor
import com.eren.ecommerce.product.Product;

// JPA ve Lombok kütüphanelerinden gerekli anotasyonlar import ediliyor
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * `Category` sinifi, bir urun kategorisini temsil eder.
 * Bu sinif, JPA kullanarak bir veritabani tablosunu temsil eder.
 */
@AllArgsConstructor // Tüm alanlar için bir yapıcı (constructor) oluşturur
@NoArgsConstructor // Parametresiz bir yapıcı (constructor) oluşturur
@Builder // Builder deseni ile nesne oluşturmayı kolaylaştırır
@Getter // Tüm alanlar için getter metodlarını oluşturur
@Setter // Tüm alanlar için setter metodlarını oluşturur
@Entity // Bu sinifin JPA varligi (entity) olduğunu belirtir. Bu, bir veritabani tablosuna karşılık gelir.
public class Category {

    /**
     * Kategori ID'si. Veritabani tarafından otomatik olarak üretilir.
     */
    @Id // Bu alanın birincil anahtar (primary key) olduğunu belirtir
    @GeneratedValue // Bu alanın veritabanı tarafından otomatik olarak üretileceğini belirtir
    private Integer id; // Kategori'nin benzersiz ID'si

    /**
     * Kategori adı.
     */
    private String name; // Kategori'nin adı

    /**
     * Kategori açıklaması.
     */
    private String description; // Kategori'nin açıklaması

    /**
     * Kategoriye ait ürünlerin listesi.
     * `mappedBy` özelliği, `Product` sınıfındaki `category` özelliğine karşılık gelir.
     * `cascade = CascadeType.REMOVE` ayarı, kategori silindiğinde ilgili ürünlerin de silinmesini sağlar.
     */
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE) // Bir kategoriye ait birden fazla ürün olabilir
    private List<Product> products; // Bu kategoriye ait ürünlerin listesi
}
package com.eren.ecommerce.product;

import com.eren.ecommerce.category.Category;
import org.springframework.stereotype.Service;

// `@Service` annotasyonu, bu sınıfın bir servis bileşeni olduğunu belirtir.
// Bu, genellikle iş mantığını kapsayan sınıflar için kullanılır ve Spring tarafından otomatik olarak yönetilir.
@Service
public class ProductMapper {

    // `toProduct` metodu, `ProductRequest` nesnesini `Product` nesnesine dönüştürür.
    public Product toProduct(ProductRequest request) {
        return Product.builder()
                // `ProductRequest` nesnesinden değerleri alarak `Product` nesnesi oluşturur.
                .id(request.id()) // Ürün ID'sini alır.
                .name(request.name()) // Ürün adını alır.
                .description(request.description()) // Ürün açıklamasını alır.
                .availableQuantity(request.availableQuantity()) // Ürün stok miktarını alır.
                .price(request.price()) // Ürün fiyatını alır.
                .category(
                        // `ProductRequest`'ten alınan kategori ID'si ile bir `Category` nesnesi oluşturur.
                        Category.builder()
                                .id(request.categoryId()) // Kategori ID'sini alır.
                                .build() // `Category` nesnesini oluşturur.
                )
                .build(); // `Product` nesnesini oluşturur.
    }

    // `toProductResponse` metodu, `Product` nesnesini `ProductResponse` nesnesine dönüştürür.
    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(), // Ürün ID'sini alır.
                product.getName(), // Ürün adını alır.
                product.getDescription(), // Ürün açıklamasını alır.
                product.getAvailableQuantity(), // Ürün stok miktarını alır.
                product.getPrice(), // Ürün fiyatını alır.
                product.getCategory().getId(), // Kategori ID'sini alır.
                product.getCategory().getName(), // Kategori adını alır.
                product.getCategory().getDescription() // Kategori açıklamasını alır.
        );
    }

    // `toProductPurchaseResponse` metodu, `Product` nesnesini ve miktarı alarak bir `ProductPurchaseResponse` nesnesi oluşturur.
    public ProductPurchaseResponse toProductPurchaseResponse(Product product, double quantity) {
        return new ProductPurchaseResponse(
                product.getId(), // Ürün ID'sini alır.
                product.getName(), // Ürün adını alır.
                product.getDescription(), // Ürün açıklamasını alır.
                product.getPrice(), // Ürün fiyatını alır.
                quantity // Satın alınan miktarı alır.
        );
    }
}
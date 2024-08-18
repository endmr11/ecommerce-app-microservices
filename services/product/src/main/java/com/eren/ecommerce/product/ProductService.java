package com.eren.ecommerce.product;

import com.eren.ecommerce.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// `@Service` anotasyonu, bu sınıfın bir servis bileşeni olduğunu belirtir.
// Spring'in bu sınıfı bir bileşen olarak yönetmesini sağlar.
@Service

// `@RequiredArgsConstructor` anotasyonu, bu sınıfa tüm final alanlar için bir yapıcı (constructor) sağlar.
// Bu, bağımlılık enjeksiyonunu kolaylaştırır ve yapılandırıcı enjeksiyon sağlar.
@RequiredArgsConstructor
public class ProductService {

    // `ProductRepository`, ürün verileri ile etkileşim için kullanılan JPA repository'sidir.
    // Bu, ürün verilerini veri tabanından almak ve kaydetmek için kullanılır.
    private final ProductRepository repository;

    // `ProductMapper`, `ProductRequest` ve `Product` arasında dönüşüm yapar.
    // Aynı zamanda `ProductResponse` ve `ProductPurchaseResponse` nesnelerine dönüştürmek için kullanılır.
    private final ProductMapper mapper;

    // Ürün oluşturma işlemini gerçekleştirir.
    // `ProductRequest` nesnesini alır, bir `Product` nesnesine dönüştürür ve bu ürünü veri tabanına kaydeder.
    // Kaydedilen ürünün kimliğini döndürür.
    public Integer createProduct(ProductRequest request) {
        var product = mapper.toProduct(request);
        return repository.save(product).getId();
    }

    // Belirtilen ID'ye sahip ürünü bulur.
    // Ürün mevcutsa `ProductResponse` nesnesine dönüştürür; aksi takdirde bir `EntityNotFoundException` fırlatır.
    public ProductResponse findById(Integer id) {
        return repository.findById(id)
                .map(mapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Kimliğe sahip ürün bulunamadı: " + id));
    }

    // Tüm ürünleri bulur ve her birini `ProductResponse` nesnesine dönüştürür.
    // Sonuçları bir liste olarak döndürür.
    public List<ProductResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toProductResponse)
                .collect(Collectors.toList());
    }

    // Ürünleri satın alma işlemini gerçekleştirir.
    // `ProductPurchaseRequest` listesi alır ve her bir ürünü kontrol eder.
    // Ürünlerin mevcut stok miktarını kontrol eder ve yeterli miktarda stok varsa miktarı günceller.
    // Satın alınan ürünlerin detaylarını `ProductPurchaseResponse` nesnesine dönüştürür ve bir liste olarak döndürür.
    @Transactional(rollbackFor = ProductPurchaseException.class)
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        // İsteklerden ürün ID'lerini alır.
        var productIds = request
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();

        // ID'lere göre ürünleri veri tabanından alır.
        var storedProducts = repository.findAllByIdInOrderById(productIds);

        // İsteklerdeki ürün sayısı ile alınan ürün sayısı uyuşmuyorsa bir istisna fırlatır.
        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("Bir veya daha fazla ürün mevcut değil");
        }

        // İstekleri ürün ID'sine göre sıralar.
        var sortedRequest = request
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();

        // Satın alınan ürünlerin yanıtlarını saklamak için bir liste oluşturur.
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();

        // Ürünleri ve istekleri eşleştirir ve satın alma işlemini gerçekleştirir.
        for (int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = sortedRequest.get(i);

            // Ürünün stok miktarını kontrol eder. Yeterli miktarda stok yoksa bir istisna fırlatır.
            if (product.getAvailableQuantity() < productRequest.quantity()) {
                throw new ProductPurchaseException("Kimlikli ürün için stok miktarı yetersiz: " + productRequest.productId());
            }

            // Yeni stok miktarını hesaplar ve günceller.
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            repository.save(product);

            // Satın alınan ürünün yanıtını oluşturur ve listeye ekler.
            purchasedProducts.add(mapper.toProductPurchaseResponse(product, productRequest.quantity()));
        }

        // Satın alınan ürünlerin yanıtlarını döndürür.
        return purchasedProducts;
    }

}
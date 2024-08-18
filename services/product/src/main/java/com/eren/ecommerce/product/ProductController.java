package com.eren.ecommerce.product;

// Gerekli importlar
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// `@RestController` annotasyonu, bu sınıfın bir REST API denetleyici olduğunu belirtir.
// Bu, HTTP isteklerini işleyen ve JSON/XML yanıtları döndüren bir denetleyicidir.
@RestController

// `@RequestMapping` annotasyonu, bu denetleyicinin işlediği ana URL yolunu belirtir.
// Burada, bu denetleyici tüm istekleri `/api/v1/products` yoluna yönlendirir.
@RequestMapping("/api/v1/products")

// `@RequiredArgsConstructor` annotasyonu, final alanlar için bir yapıcı (constructor) oluşturur.
// Bu, `ProductService` alanını başlatmak için gereklidir.
@RequiredArgsConstructor
public class ProductController {

    // `ProductService` sınıfının bir örneği
    // Bu sınıf, ürünlerle ilgili iş mantığını içerir.
    private final ProductService service;

    // `@PostMapping` annotasyonu, bu metodun HTTP POST isteklerini işlediğini belirtir.
    // `/api/v1/products` yoluna yapılan POST isteklerine yanıt verir.
    @PostMapping
    public ResponseEntity<Integer> createProduct(
            // `@RequestBody` annotasyonu, HTTP isteğindeki gövdenin bu metoda parametre olarak alındığını belirtir.
            // `@Valid` annotasyonu, `ProductRequest` nesnesinin doğrulama kurallarına uyduğunu kontrol eder.
            @RequestBody @Valid ProductRequest request
    ) {
        // `ProductService` sınıfındaki `createProduct` metodunu çağırır ve yanıt olarak ürünün ID'sini döner.
        return ResponseEntity.ok(service.createProduct(request));
    }

    // `@PostMapping("/purchase")` annotasyonu, bu metodun `/api/v1/products/purchase` yoluna yapılan POST isteklerini işlediğini belirtir.
    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(
            // `@RequestBody` annotasyonu, HTTP isteğindeki gövdenin bu metoda parametre olarak alındığını belirtir.
            @RequestBody List<ProductPurchaseRequest> request
    ) {
        // `ProductService` sınıfındaki `purchaseProducts` metodunu çağırır ve yanıt olarak satın alınan ürünlerin yanıtlarını döner.
        return ResponseEntity.ok(service.purchaseProducts(request));
    }

    // `@GetMapping("/{product-id}")` annotasyonu, bu metodun `/api/v1/products/{product-id}` yoluna yapılan GET isteklerini işlediğini belirtir.
    // `{product-id}` bir yol değişkenidir (path variable) ve metodun parametresi olarak alınır.
    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> findById(
            // `@PathVariable` annotasyonu, URL yolundan alınan `product-id` değerini metoda parametre olarak alır.
            @PathVariable("product-id") Integer productId
    ) {
        // `ProductService` sınıfındaki `findById` metodunu çağırır ve yanıt olarak ürün bilgilerini döner.
        return ResponseEntity.ok(service.findById(productId));
    }

    // `@GetMapping` annotasyonu, bu metodun `/api/v1/products` yoluna yapılan GET isteklerini işlediğini belirtir.
    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        // `ProductService` sınıfındaki `findAll` metodunu çağırır ve yanıt olarak tüm ürünlerin bilgilerini döner.
        return ResponseEntity.ok(service.findAll());
    }
}
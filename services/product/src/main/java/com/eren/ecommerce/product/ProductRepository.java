package com.eren.ecommerce.product;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// `ProductRepository`, JPA (Java Persistence API) kullanarak veri erişimini sağlayan bir repository arayüzüdür.
// `JpaRepository<Product, Integer>` arayüzünü genişletir ve `Product` veri sınıfı üzerinde CRUD (Create, Read, Update, Delete) işlemleri sağlar.
public interface ProductRepository extends JpaRepository<Product, Integer> {

    // `findAllByIdInOrderById` metodu, belirli bir ürün kimlikleri listesine sahip ürünleri getirir.
    // Bu metodun açıklamaları:

    // `List<Product>`: Bu metodun döndürdüğü değer, ürünlerin bir listesidir.
    // `findAllByIdInOrderById`: Bu metod, verilen ürün kimliklerinin bulunduğu ürünleri getirir.
    // Bu metodun özelliği, ürünleri `id` alanına göre sıralamasıdır.
    // `List<Integer> ids`: Ürünlerin kimliklerini içeren bir liste alır.
    // Ürünler bu kimlikler listesinde yer alan kimliklerle eşleşir.
    List<Product> findAllByIdInOrderById(List<Integer> ids);
}
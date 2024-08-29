# Mikroservis Mimari Şeması

## Who made it? It's Eren ;)

## 1. API Gateway (API GW)
- **Amaç:** Dış dünyadan gelen tüm istekler API Gateway üzerinden geçer. Bu, bir giriş noktası sağlar ve tüm mikroservislerin direkt olarak internete açılmasına gerek kalmaz.
- **Kullanım:** Gelen istekleri alır, doğru mikroservise yönlendirir. Örneğin, `/customers` isteği Customer servisine, `/products` isteği Product servisine, `/orders` isteği Order servisine yönlendirilir.

## 2. Customer Servisi
- **Amaç:** Müşteri bilgilerini yönetir.
- **Kullanım:** Müşteri verilerini bir MongoDB veritabanında saklar. Müşteri kaydı oluşturma, güncelleme gibi işlemler bu servis üzerinden gerçekleştirilir.

## 3. Product Servisi
- **Amaç:** Ürün bilgilerini yönetir.
- **Kullanım:** Ürün verilerini bir MongoDB veritabanında saklar. Ürün ekleme, güncelleme ve listeleme gibi işlemler bu servis aracılığıyla yapılır.

## 4. Order Servisi
- **Amaç:** Sipariş işlemlerini yönetir.
- **Kullanım:** Sipariş oluşturma ve sipariş bilgilerini saklama işlevini yerine getirir. Bir sipariş oluşturulduğunda, ürün bilgilerini Product servisinden, müşteri bilgilerini ise Customer servisinden alır.

## 5. Payment Servisi
- **Amaç:** Ödeme işlemlerini yönetir.
- **Kullanım:** Bir sipariş oluşturulduğunda, ödeme işlemini gerçekleştirir ve ödemeyi onaylar. Ödeme onay bilgilerini Kafka aracılığıyla Notification servisine gönderir.

## 6. Notification Servisi
- **Amaç:** Bildirim göndermek.
- **Kullanım:** Payment ve Order servislerinden gelen asenkron mesajları dinler ve müşteriye sipariş durumu hakkında e-posta gibi bildirimler gönderir.

## 7. Kafka (Message Broker)
- **Amaç:** Mikroservisler arasındaki asenkron iletişimi sağlamak.
- **Kullanım:** Payment ve Order servislerinden gelen onay mesajlarını Notification servisine iletir. Bu sayede servisler birbirinden bağımsız çalışabilir ve aralarında sıkı bir bağ olmaz.

## 8. Zipkin (Distributed Tracing)
- **Amaç:** Dağıtık izleme ve performans takibi.
- **Kullanım:** Servisler arasındaki isteklerin izlenmesi ve performans ölçümlerinin yapılmasını sağlar. Hangi servislerin ne kadar sürede yanıt verdiğini analiz etmek için kullanılır.

## 9. Eureka Server
- **Amaç:** Servis keşfi ve yönetimi.
- **Kullanım:** Mikroservislerin nerede ve hangi IP adresinde çalıştığını bulmak için kullanılır. Her mikroservis bu sunucuya kendisini kaydeder ve diğer servisler bu sunucu üzerinden birbirlerine ulaşabilir.

## 10. Config Server
- **Amaç:** Merkezi yapılandırma yönetimi.
- **Kullanım:** Tüm mikroservislerin yapılandırma dosyalarını merkezi bir yerden yönetmek için kullanılır. Örneğin, bir veritabanı bağlantı adresi değiştiğinde, tüm servislerde bu değişikliğin tek bir yerden yapılması sağlanır.

## 11. MongoDB
- **Amaç:** Veritabanı olarak hizmet etmek.
- **Kullanım:** Customer, Product, Order ve Notification servislerinin veri saklamak için kullandığı NoSQL veritabanıdır.

## 12. Docker
- **Amaç:** Uygulamaların konteynerize edilmesi.
- **Kullanım:** Mikroservislerin farklı ortamlarda aynı şekilde çalışmasını sağlamak için kullanılır. Örneğin, her servis Docker konteynerleri içinde çalıştırılabilir.


# E-Ticaret Sitesi Alışveriş Süreci

## 1. Müşteri Kaydı (Customer Registration)
**Senaryo:** Joe adında bir kullanıcı e-ticaret sitesine üye olmaya karar verir.

### Adımlar:
1. Joe, adını, soyadını ve e-posta adresini girerek siteye kaydolur.
2. Veritabanında `Customer` tablosuna yeni bir kayıt eklenir. Joe’in kullanıcı bilgileri (ad, soyad, e-posta) bu tabloya kaydedilir.
3. Joe, aynı zamanda adres bilgilerini de sisteme girer (örneğin: `street: "Atatürk Cad."`, `houseNumber: "10"`, `zipCode: "34560"`).
4. Bu adres bilgisi `Address` tablosunda saklanır ve `Customer` tablosuyla ilişkilendirilir.

## 2. Ürün Arama ve Seçimi (Product Search and Selection)
**Senaryo:** Joe, sitede gezerek ilgilendiği ürünleri arar ve sepete ekler.

### Adımlar:
1. Joe, çeşitli kategorilere göz atar. Örneğin, `Category` tablosundan “Elektronik” kategorisini seçer.
2. Bu kategoriye ait ürünler `Product` tablosundan getirilir (örneğin: “Laptop”, “Cep Telefonu”).
3. Joe, bir laptopu sepetine ekler. Bu ürünün adı, açıklaması, fiyatı ve mevcut miktarı `Product` tablosunda bulunur.

## 3. Sipariş Verme (Placing an Order)
**Senaryo:** Joe, sepetindeki ürünleri satın almaya karar verir ve siparişini tamamlar.

### Adımlar:
1. Joe, ödeme adımına geçer. Sipariş oluşturulur ve bu siparişin detayları `Order` tablosunda saklanır (örneğin: `orderDate`, `reference`).
2. Sipariş içerisindeki her ürün için `OrderLine` tablosuna bir kayıt eklenir (örneğin: `id: 1`, `quantity: 1`).
3. Bu siparişin `OrderLine` tabloları ile olan ilişkisi, Joe’in hangi üründen kaç adet sipariş verdiğini gösterir.

## 4. Ödeme İşlemi (Payment Processing)
**Senaryo:** Joe, siparişinin ödemesini yapmak için kredi kartı bilgilerini girer.

### Adımlar:
1. Joe, kredi kartı bilgilerini girip ödemeyi tamamladığında, ödeme bilgileri `Payment` tablosunda saklanır (örneğin: `reference`, `amount`, `status`).
2. Bu `Payment` kaydı, ilgili `Order` kaydıyla ilişkilendirilir. Bu, Joe’in siparişine ait ödeme bilgilerini gösterir.

## 5. Sipariş Onayı ve Bildirim (Order Confirmation and Notification)
**Senaryo:** Joe’in siparişi başarıyla tamamlanır ve ona bir bildirim gönderilir.

### Adımlar:
1. Sipariş tamamlandığında, sistem otomatik olarak Joe’in e-posta adresine bir bildirim gönderir.
2. Bu bildirim, `Notification` tablosunda saklanır. Bildirimin içeriği (`content`), gönderen (`sender`), alıcı (`recipient`) ve tarih (`date`) bilgileri bu tabloda yer alır.
3. Bu bildirim, ilgili sipariş (`Order`) ile ilişkilendirilir.

## 6. Ürün Teslimatı (Product Delivery)
**Senaryo:** Joe’in siparişi kargoya verilir ve belirtilen adrese teslim edilir.

### Adımlar:
1. Siparişin durumu güncellenir ve kargo bilgileri eklenir.
2. Ürünlerin stok miktarları güncellenir (`availableQuantity` azalır).
3. Joe’e siparişinin teslim edildiğine dair bir bildirim gönderilir ve `Notification` tablosında kayıt altına alınır.

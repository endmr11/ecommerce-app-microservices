package com.eren.ecommerce.customer;
// Bu sınıf, uygulamanın `com.eren.ecommerce.customer` paketinde olduğunu belirtir.

import com.eren.ecommerce.exception.CustomerNotFoundException;
// `CustomerNotFoundException` sınıfını import eder; müşteri bulunamadığında fırlatılan özel bir istisnadır.

import io.micrometer.common.util.StringUtils;
// Boşluk kontrolü ve string işleme için Micrometer'ın `StringUtils` sınıfını import eder.

import jakarta.validation.Valid;
// `@Valid` notasyonu, metod parametrelerinin doğrulanmasını sağlar.

import lombok.RequiredArgsConstructor;
// Lombok'un `@RequiredArgsConstructor` notasyonunu import eder; gerekli alanlar için bir constructor sağlar.

import org.springframework.stereotype.Service;
// Bu sınıfın bir servis bileşeni olduğunu belirtir; iş mantığı içerir.

import java.util.List;
// Java'nın List koleksiyon türünü import eder.

import java.util.stream.Collectors;
// Java Streams API'nin `Collectors` sınıfını import eder; akışları koleksiyonlara dönüştürür.

@Service
@RequiredArgsConstructor
// Bu sınıfın bir servis bileşeni olduğunu belirtir ve `RequiredArgsConstructor` ile gerekli alanlar için constructor sağlar.
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    // `CustomerRepository` veri erişimi ve `CustomerMapper` veri dönüştürme için kullanılan bağımlılıklar.

    public String createCustomer(@Valid CustomerRequest request) {
        var customer = customerRepository.save(customerMapper.toCustomer(request));
        // `CustomerRequest`'ten `Customer` nesnesini oluşturur ve veritabanına kaydeder.
        return customer.getId();
        // Kaydedilen müşteri nesnesinin ID'sini döner.
    }

    public void updateCustomer(CustomerRequest request) {
        var customer = this.customerRepository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Müşteri güncellenemiyor: Sağlanan kimliğe sahip müşteri bulunamadı: %s", request.id())
                ));
        // Veritabanından müşteri ID'si ile müşteri nesnesini bulur. Bulamazsa özel bir istisna fırlatır.
        mergeCustomer(customer, request);
        // Müşteri nesnesini günceller.
        this.customerRepository.save(customer);
        // Güncellenmiş müşteri nesnesini veritabanına kaydeder.
    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if (StringUtils.isNotBlank(request.firstName())) {
            customer.setFirstName(request.firstName());
        }
        if (StringUtils.isNotBlank(request.email())) {
            customer.setEmail(request.email());
        }
        if (request.address() != null) {
            customer.setAddress(request.address());
        }
        // `CustomerRequest` içindeki alanları, mevcut `Customer` nesnesine kopyalar, boş değerler hariç.
    }

    public List<CustomerResponse> findAllCustomers() {
        return this.customerRepository.findAll()
                .stream()
                .map(this.customerMapper::fromCustomer)
                .collect(Collectors.toList());
        // Veritabanındaki tüm müşterileri bulur, her birini `CustomerResponse` nesnesine dönüştürür ve bir liste döner.
    }

    public CustomerResponse findById(String id) {
        return this.customerRepository.findById(id)
                .map(customerMapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Sağlanan kimlikle müşteri bulunamadı: %s", id)));
        // Verilen ID ile müşteri bulur, varsa `CustomerResponse` nesnesine dönüştürür. Bulamazsa özel bir istisna fırlatır.
    }

    public boolean existsById(String id) {
        return this.customerRepository.findById(id)
                .isPresent();
        // Verilen ID ile müşteri olup olmadığını kontrol eder ve boolean döner.
    }

    public void deleteCustomer(String id) {
        this.customerRepository.deleteById(id);
        // Verilen ID ile müşteri nesnesini veritabanından siler.
    }
}
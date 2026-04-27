package com.example.ecommerce_backoffice.customer.repository;

import com.example.ecommerce_backoffice.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAllByDeletedAtIsNull();
    Optional<Customer> findAllByDeletedAtIsNull(Long id);
}

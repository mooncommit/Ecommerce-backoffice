package com.example.ecommerce_backoffice.customer.repository;

import com.example.ecommerce_backoffice.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

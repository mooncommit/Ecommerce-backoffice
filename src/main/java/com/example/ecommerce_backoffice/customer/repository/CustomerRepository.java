package com.example.ecommerce_backoffice.customer.repository;

import com.example.ecommerce_backoffice.customer.entity.Customer;
import com.example.ecommerce_backoffice.customer.enums.CustomerStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c WHERE c.deletedAt IS NULL AND " +
            "(:keyword IS NULL OR c.name LIKE %:keyword% OR c.email LIKE %:keyword%) AND " +
            "(:status IS NULL OR c.status = :status)")
    Page<Customer> findCustomer(
            @Param("keyword") String keyword,
            @Param("status") CustomerStatus status,
            Pageable pageable
    );
    Optional<Customer> findByIdAndDeletedAtIsNull(Long id);
}

package com.example.ecommerce_backoffice.admin.repository;

import com.example.ecommerce_backoffice.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}

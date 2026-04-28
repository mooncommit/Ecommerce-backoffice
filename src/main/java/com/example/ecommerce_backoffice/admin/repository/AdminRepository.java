package com.example.ecommerce_backoffice.admin.repository;

import com.example.ecommerce_backoffice.admin.entity.Admin;
import com.example.ecommerce_backoffice.admin.enums.AdminRole;
import com.example.ecommerce_backoffice.admin.enums.AdminStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Query("""
            SELECT a
            FROM Admin a
            WHERE (:keyword IS NULL OR :keyword = '' OR a.name LIKE %:keyword% OR a.email LIKE %:keyword%)
              AND (:role IS NULL OR a.role = :role)
              AND (:status IS NULL OR a.status = :status)
            """)
    Page<Admin> findAdmins(
            @Param("keyword") String keyword,
            @Param("role") AdminRole role,
            @Param("status") AdminStatus status,
            Pageable pageable);
}

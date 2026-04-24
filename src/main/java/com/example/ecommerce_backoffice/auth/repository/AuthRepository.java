package com.example.ecommerce_backoffice.auth.repository;

import com.example.ecommerce_backoffice.admin.entity.Admin;
import com.example.ecommerce_backoffice.auth.dto.RegisterCreateRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AuthRepository extends JpaRepository<Admin, Long> {

    Optional<RegisterCreateRequestDto> findByEmail(String email);
    boolean existsByEmail(String email);

}

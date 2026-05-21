package com.example.stud_erp.repository;

import com.example.stud_erp.entity.Accountant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountantRepository extends JpaRepository<Accountant, Long> {

    Optional<Accountant> findByEmail(String email);
}
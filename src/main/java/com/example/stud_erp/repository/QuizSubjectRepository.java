package com.example.stud_erp.repository;

import com.example.stud_erp.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizSubjectRepository extends JpaRepository<Subject, Long> {
}
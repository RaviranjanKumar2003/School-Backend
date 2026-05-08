package com.example.stud_erp.repository;

import com.example.stud_erp.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Professor findByUsernameAndPassword(String username, String password);
    @Query("""
    SELECT DISTINCT p
    FROM Professor p
    JOIN p.assignments a
    WHERE a.className = :className
    """)
    List<Professor> findTeachersByClassName(
            @Param("className") String className
    );
}
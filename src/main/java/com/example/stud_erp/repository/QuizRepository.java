package com.example.stud_erp.repository;

import com.example.stud_erp.entity.Quiz;
import com.example.stud_erp.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    List<Quiz> findBySubject(Subject subject);
    List<Quiz> findBySubjectId(Long subjectId);

}
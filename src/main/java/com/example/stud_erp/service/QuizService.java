package com.example.stud_erp.service;

import com.example.stud_erp.entity.Quiz;
import com.example.stud_erp.entity.Subject;
import com.example.stud_erp.payload.request.QuizRequest;
import com.example.stud_erp.repository.ProfessorRepository;
import com.example.stud_erp.repository.QuizRepository;
import com.example.stud_erp.repository.QuizSubjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizSubjectRepository quizSubjectRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    // ================= GET QUIZ BY SUBJECT =================

    public List<Quiz> getQuizBySubject(Long subjectId) {

        // 🔥 FIX: correct JPA nested field mapping
        return quizRepository.findBySubjectId(subjectId);
    }

    // ================= CREATE QUIZ =================

    public Quiz createQuiz(QuizRequest request) {

        // ===== VALIDATE SUBJECT =====

        Subject subject = quizSubjectRepository.findById(request.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found with id: " + request.getSubjectId()));

        // ===== CREATE QUIZ ENTITY =====

        Quiz quiz = new Quiz();

        quiz.setQuestionTitle(request.getQuestionTitle());
        quiz.setOptionA(request.getOptionA());
        quiz.setOptionB(request.getOptionB());
        quiz.setOptionC(request.getOptionC());
        quiz.setOptionD(request.getOptionD());
        quiz.setCorrectAnswer(request.getCorrectAnswer());
        quiz.setChapterName(request.getChapterName());

        // 🔥 IMPORTANT: proper relation mapping
        quiz.setSubject(subject);

        return quizRepository.save(quiz);
    }
}
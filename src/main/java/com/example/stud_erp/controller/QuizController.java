package com.example.stud_erp.controller;

import com.example.stud_erp.entity.Quiz;
import com.example.stud_erp.payload.request.QuizRequest;
import com.example.stud_erp.service.QuizService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
@CrossOrigin("*")
public class QuizController {

    @Autowired
    private QuizService quizService;

    // ================= CREATE QUIZ =================

    @PostMapping
    public Quiz createQuiz(@RequestBody QuizRequest request) {

        return quizService.createQuiz(request);
    }

    // ================= GET QUIZZES BY SUBJECT =================

    @GetMapping("/subject/{subjectId}")
    public List<Quiz> getQuizBySubject(
            @PathVariable Long subjectId
    ) {

        return quizService.getQuizBySubject(subjectId);
    }
}
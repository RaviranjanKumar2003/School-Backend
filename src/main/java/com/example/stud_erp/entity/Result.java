package com.example.stud_erp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "results")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔹 Student Info
    private Long studentId;

    // 🔹 Subject Info
    private String subject;

    // 🔹 Marks
    private int marks;

    // 🔹 Teacher Info
    private Long professorId;

    // 🔥 NEW FIELDS (CORE SYSTEM)
    private String status;        // PASS / FAIL / ABSENT
    private Double percentage;   // overall percentage
    private String grade;        // A+, A, B...

    // 🔹 Constructors
    public Result() {}

    public Result(Long studentId, String subject, int marks, Long professorId) {
        this.studentId = studentId;
        this.subject = subject;
        this.marks = marks;
        this.professorId = professorId;
    }

    // 🔹 Getters & Setters

    public Long getId() {
        return id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    // 🔥 OPTIONAL (AUTO LOGIC - ADVANCED USE)
    @PrePersist
    @PreUpdate
    public void calculateStatus() {
        if (marks == 0) {
            this.status = "ABSENT";
        } else if (marks < 33) {
            this.status = "FAIL";
        } else {
            this.status = "PASS";
        }
    }
}
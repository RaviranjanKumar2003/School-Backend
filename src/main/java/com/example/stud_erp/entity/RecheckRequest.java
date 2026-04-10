package com.example.stud_erp.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "recheck_requests")
public class RecheckRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔹 Student Info
    private Long studentId;
    private String studentName;

    // 🔥 MULTIPLE SUBJECT SUPPORT
    @ElementCollection
    private List<String> subjects;

    // 🔹 Request Status
    // PENDING -> APPROVED -> COMPLETED / REJECTED
    private String status;

    // 🔹 Optional Reason
    private String reason;

    // 🔹 Teacher (who will recheck)
    private Long professorId;

    // 🔹 Timestamps
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 🔹 Constructor
    public RecheckRequest() {
        this.status = "PENDING";
        this.createdAt = LocalDateTime.now();
    }

    // 🔥 AUTO UPDATE TIME
    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // 🔹 Getters & Setters

    public Long getId() {
        return id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public String getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }
}
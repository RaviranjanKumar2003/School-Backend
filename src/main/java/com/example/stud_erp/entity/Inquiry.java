package com.example.stud_erp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "inquiries")
public class Inquiry {

    // =====================================
    // ID
    // =====================================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // =====================================
    // STUDENT INFO
    // =====================================

    private String studentName;

    private String parentName;

    private String phone;

    private String email;

    @Column(length = 3000)
    private String message;

    // =====================================
    // SCHOOL
    // =====================================

    private String schoolCode;

    private String schoolName;

    // =====================================
    // STATUS
    // =====================================

    private String status = "PENDING";

    // =====================================
    // TIME
    // =====================================

    private LocalDateTime createdAt;

    // =====================================
    // AUTO TIME
    // =====================================

    @PrePersist
    public void onCreate() {

        createdAt = LocalDateTime.now();
    }


// GETTERS & SETTERS


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
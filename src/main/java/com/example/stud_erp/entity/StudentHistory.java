package com.example.stud_erp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class StudentHistory {

    @Id
    @GeneratedValue
    private Long id;

    private Long studentId;

    private Long oldClassId;
    private Long newClassId;

    private String oldSection;
    private String newSection;

    private Long updatedBy;   // admin id
    private String updatedByName;

    private String actionType; // PROMOTION / TRANSFER

    private LocalDateTime updatedAt;


// GETTERS & SETTERS


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getOldClassId() {
        return oldClassId;
    }

    public void setOldClassId(Long oldClassId) {
        this.oldClassId = oldClassId;
    }

    public Long getNewClassId() {
        return newClassId;
    }

    public void setNewClassId(Long newClassId) {
        this.newClassId = newClassId;
    }

    public String getOldSection() {
        return oldSection;
    }

    public void setOldSection(String oldSection) {
        this.oldSection = oldSection;
    }

    public String getNewSection() {
        return newSection;
    }

    public void setNewSection(String newSection) {
        this.newSection = newSection;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
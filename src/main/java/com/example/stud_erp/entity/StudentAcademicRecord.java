package com.example.stud_erp.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "student_academic_records")
public class StudentAcademicRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ================= STUDENT =================

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    // ================= SESSION =================

    @ManyToOne
    @JoinColumn(name = "session_id")
    private AcademicSession session;

    // ================= CLASS =================

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassEntity classEntity;

    private String section;

    private Long rollNo;

    // ================= OLD DATA =================

    private String previousClass;

    private String previousSection;

    // ================= PROMOTION =================

    private LocalDate promotedDate;

    private Long promotedBy;

    private String remarks;

    // Current active academic record
    private boolean currentRecord = true;

    private LocalDateTime createdAt;

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
    }

// ================= GETTER SETTER =================

    public Long getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public AcademicSession getSession() {
        return session;
    }

    public void setSession(AcademicSession session) {
        this.session = session;
    }

    public ClassEntity getClassEntity() {
        return classEntity;
    }

    public void setClassEntity(ClassEntity classEntity) {
        this.classEntity = classEntity;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Long getRollNo() {
        return rollNo;
    }

    public void setRollNo(Long rollNo) {
        this.rollNo = rollNo;
    }

    public String getPreviousClass() {
        return previousClass;
    }

    public void setPreviousClass(String previousClass) {
        this.previousClass = previousClass;
    }

    public String getPreviousSection() {
        return previousSection;
    }

    public void setPreviousSection(String previousSection) {
        this.previousSection = previousSection;
    }

    public LocalDate getPromotedDate() {
        return promotedDate;
    }

    public void setPromotedDate(LocalDate promotedDate) {
        this.promotedDate = promotedDate;
    }

    public Long getPromotedBy() {
        return promotedBy;
    }

    public void setPromotedBy(Long promotedBy) {
        this.promotedBy = promotedBy;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public boolean isCurrentRecord() {
        return currentRecord;
    }

    public void setCurrentRecord(boolean currentRecord) {
        this.currentRecord = currentRecord;
    }
}
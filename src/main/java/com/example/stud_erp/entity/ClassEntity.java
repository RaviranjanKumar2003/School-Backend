package com.example.stud_erp.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "classes")
public class ClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String className;

    private Integer classNumber;

    // ⭐ IMPORTANT: SCHOOL LINK
    private Long schoolId;

    @OneToMany(mappedBy = "classEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subject> subjects;

// GETTERS & SETTERS


    public Integer getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(Integer classNumber) {
        this.classNumber = classNumber;
    }

    public Long getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
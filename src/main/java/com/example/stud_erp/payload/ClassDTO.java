package com.example.stud_erp.payload;

import java.util.List;

public class ClassDTO {

    private Long id;
    private String className;
    private List<SubjectDTO> subjects;

// GETTERS & SETTERS
    public Long getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }

    public List<SubjectDTO> getSubjects() {
        return subjects;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setSubjects(List<SubjectDTO> subjects) {
        this.subjects = subjects;
    }
}
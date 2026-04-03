package com.example.stud_erp.payload;

public class SubjectDTO {

    private Long id;
    private String subjectName;
    private Long classId;

    // GETTERS & SETTERS
    public Long getId() {
        return id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public Long getClassId() {
        return classId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }
}
package com.example.stud_erp.payload;

import java.util.List;

public class ClassDTO {

    private Long id;
    private String className;
    private Integer classNumber;
    private Long schoolId;   // ⭐ IMPORTANT

    private List<SubjectDTO> subjects;

    private List<ClassSectionDto> sections;

// GETTERS & SETTERS


    public List<ClassSectionDto> getSections() {
        return sections;
    }

    public void setSections(List<ClassSectionDto> sections) {
        this.sections = sections;
    }

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

    public List<SubjectDTO> getSubjects() {
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

    public void setSubjects(List<SubjectDTO> subjects) {
        this.subjects = subjects;
    }
}
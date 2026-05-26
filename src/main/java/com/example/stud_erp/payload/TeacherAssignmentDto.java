package com.example.stud_erp.payload;

public class TeacherAssignmentDto {

    private Long id;

    // =====================================================
    // PROFESSOR
    // =====================================================

    private Long professorId;

    private String professorName;

    // =====================================================
    // SCHOOL
    // =====================================================

    private Long schoolId;

    // =====================================================
    // CLASS
    // =====================================================

    private Long classId;

    private String className;

    // =====================================================
    // SECTION
    // =====================================================

    private String section;

    // =====================================================
    // SUBJECT
    // =====================================================

    private String subjectName;

    // =====================================================
    // WEEKLY PERIODS
    // =====================================================

    private Integer weeklyPeriods;

    // =====================================================
    // STATUS
    // =====================================================

    private Boolean active;




    // =====================================================
// GETTERS & SETTERS
    // =====================================================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getWeeklyPeriods() {
        return weeklyPeriods;
    }

    public void setWeeklyPeriods(Integer weeklyPeriods) {
        this.weeklyPeriods = weeklyPeriods;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
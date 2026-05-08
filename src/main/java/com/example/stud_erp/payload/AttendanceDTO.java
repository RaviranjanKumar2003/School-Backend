package com.example.stud_erp.payload;

public class AttendanceDTO {

    // =====================================
    // STUDENT ID
    // =====================================
    private Long studentId;

    // =====================================
    // STUDENT NAME
    // =====================================
    private String studentName;

    // =====================================
    // STUDENT ROLL NUMBER
    // =====================================
    private Long studRollNo;

    // =====================================
    // ATTENDANCE STATUS (FIXED: Enum to String)
    // =====================================
    private String status;

    // =====================================
    // GETTERS
    // =====================================

    public Long getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public Long getStudRollNo() {
        return studRollNo;
    }

    // ✅ FIX: String return karega
    public String getStatus() {
        return status;
    }

    // =====================================
    // SETTERS
    // =====================================

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudRollNo(Long studRollNo) {
        this.studRollNo = studRollNo;
    }

    // ✅ FIX: String accept karega
    public void setStatus(String status) {
        this.status = status;
    }
}
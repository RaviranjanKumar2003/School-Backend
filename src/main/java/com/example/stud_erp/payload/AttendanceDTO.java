package com.example.stud_erp.payload;

import lombok.Data;

@Data
public class AttendanceDTO {

    private Long studentId;
    private String studentName;
    private String status; // P / A

// GETTERS & SETTERS


    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
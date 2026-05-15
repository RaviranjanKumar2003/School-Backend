//package com.example.stud_erp.payload;
//
//import lombok.Data;
//
//@Data
//public class StuAttendanceDTO {
//
//    private Long studentId;
//    private String studentName;
//    private String studentLastName; // ✅ ADD
//    private String email;           // ✅ ADD
//    private String status;
//    private Long studRollNo;
//
//// GETTERS & SETTERS
//
//
//    public String getStudentLastName() {
//        return studentLastName;
//    }
//
//    public void setStudentLastName(String studentLastName) {
//        this.studentLastName = studentLastName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public Long getStudentId() {
//        return studentId;
//    }
//
//    public void setStudentId(Long studentId) {
//        this.studentId = studentId;
//    }
//
//    public String getStudentName() {
//        return studentName;
//    }
//
//    public void setStudentName(String studentName) {
//        this.studentName = studentName;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public Long getStudRollNo() {
//        return studRollNo;
//    }
//
//    public void setStudRollNo(Long studRollNo) {
//        this.studRollNo = studRollNo;
//    }
//}


package com.example.stud_erp.payload;

import lombok.Data;

@Data
public class StuAttendanceDTO {

    // ================= STUDENT =================
    private Long studentId;

    private String studentName;

    private String studentLastName;

    private String email;

    private Long studRollNo;

    // ================= ATTENDANCE =================
    private String status;

    // ================= SCHOOL =================
    private Long schoolId;

    private String schoolName;

    // ================= CLASS =================
    private Long classId;

    private String className;

    // ================= TAKEN BY =================
    private Long takenById;

    private String takenByName;

    private String takenByRole;

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

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getStudRollNo() {
        return studRollNo;
    }

    public void setStudRollNo(Long studRollNo) {
        this.studRollNo = studRollNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
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

    public Long getTakenById() {
        return takenById;
    }

    public void setTakenById(Long takenById) {
        this.takenById = takenById;
    }

    public String getTakenByName() {
        return takenByName;
    }

    public void setTakenByName(String takenByName) {
        this.takenByName = takenByName;
    }

    public String getTakenByRole() {
        return takenByRole;
    }

    public void setTakenByRole(String takenByRole) {
        this.takenByRole = takenByRole;
    }
}
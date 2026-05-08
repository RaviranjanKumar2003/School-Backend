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


<<<<<<< HEAD
=======

//update


//package com.example.stud_erp.payload;
//
//public class StuAttendanceDTO {
//
//    private Long studentId;
//    private String studentName;
//    private String studentLastName;
//    private String email;
//
//    // ✅ FINAL FIX: Isko String hi rakhein (Enum hata dein)
//    private String status;
//
//    private Long studRollNo;
//
//    // ================= GETTERS =================
//
//    public Long getStudentId() {
//        return studentId;
//    }
//
//    public String getStudentName() {
//        return studentName;
//    }
//
//    public String getStudentLastName() {
//        return studentLastName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    // ✅ FIX: String return karega
//    public String getStatus() {
//        return status;
//    }
//
//    public Long getStudRollNo() {
//        return studRollNo;
//    }
//
//    // ================= SETTERS =================
//
//    public void setStudentId(Long studentId) {
//        this.studentId = studentId;
//    }
//
//    public void setStudentName(String studentName) {
//        this.studentName = studentName;
//    }
//
//    public void setStudentLastName(String studentLastName) {
//        this.studentLastName = studentLastName;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    // ✅ FIX: String accept karega
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public void setStudRollNo(Long studRollNo) {
//        this.studRollNo = studRollNo;
//    }
//}





>>>>>>> 5bf6a9a (work done)
package com.example.stud_erp.payload;

import java.time.LocalDate;
import java.time.LocalTime;

public class StuAttendanceDTO {

<<<<<<< HEAD
    // ================= STUDENT =================
=======
    // =========================================
    // STUDENT DETAILS
    // =========================================
>>>>>>> 5bf6a9a (work done)
    private Long studentId;

    private String studentName;

    private String studentLastName;

    private String email;

    private Long studRollNo;

<<<<<<< HEAD
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
=======
    // =========================================
    // ATTENDANCE DETAILS
    // =========================================
    private String status;
>>>>>>> 5bf6a9a (work done)

    private String className;

<<<<<<< HEAD
=======
    private LocalDate attendanceDate;

    private LocalTime attendanceTime;

    // =========================================
    // PROFESSOR DETAILS
    // =========================================
    private String professorName;

    // =========================================
    // SUBJECT
    // =========================================
    private String subjectName;

    // =========================================
    // GETTERS
    // =========================================

>>>>>>> 5bf6a9a (work done)
    public Long getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

<<<<<<< HEAD
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
=======
    public String getEmail() {
        return email;
>>>>>>> 5bf6a9a (work done)
    }

    public Long getStudRollNo() {
        return studRollNo;
    }

    public String getStatus() {
        return status;
    }

    public String getClassName() {
        return className;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public LocalTime getAttendanceTime() {
        return attendanceTime;
    }

    public String getProfessorName() {
        return professorName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    // =========================================
    // SETTERS
    // =========================================

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStudRollNo(Long studRollNo) {
        this.studRollNo = studRollNo;
    }

<<<<<<< HEAD
    public String getStatus() {
        return status;
    }

=======
>>>>>>> 5bf6a9a (work done)
    public void setStatus(String status) {
        this.status = status;
    }

<<<<<<< HEAD
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

=======
>>>>>>> 5bf6a9a (work done)
    public void setClassName(String className) {
        this.className = className;
    }

<<<<<<< HEAD
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
=======
    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public void setAttendanceTime(LocalTime attendanceTime) {
        this.attendanceTime = attendanceTime;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
>>>>>>> 5bf6a9a (work done)
    }
}
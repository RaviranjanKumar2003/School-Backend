//package com.example.stud_erp.entity;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//import java.time.LocalDate;
//
//@Entity
//@Data
//@Table(
//        uniqueConstraints = @UniqueConstraint(
//                columnNames = {"student_id", "classNumber", "date"}
//        )
//)
//public class StuAttendance {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private Integer classNumber;
//
//    private LocalDate date;
//
//    private String status; // P / A
//
//    @ManyToOne
//    @JoinColumn(name = "student_id", nullable = false)
//    private Student student;
//
//    // ================= NEW FIELDS 🔥 =================
//
//    private Long takenById;        // HOD ya Teacher ID
//
//    private String takenByName;   // Name store kar lo (fast access)
//
//    private String takenByRole;   // "HOD" / "TEACHER"
//
//// GETTERS & SETTERS
//
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Integer getClassNumber() {
//        return classNumber;
//    }
//
//    public void setClassNumber(Integer classNumber) {
//        this.classNumber = classNumber;
//    }
//
//    public LocalDate getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDate date) {
//        this.date = date;
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
//    public Student getStudent() {
//        return student;
//    }
//
//    public void setStudent(Student student) {
//        this.student = student;
//    }
//
//    public Long getTakenById() {
//        return takenById;
//    }
//
//    public void setTakenById(Long takenById) {
//        this.takenById = takenById;
//    }
//
//    public String getTakenByName() {
//        return takenByName;
//    }
//
//    public void setTakenByName(String takenByName) {
//        this.takenByName = takenByName;
//    }
//
//    public String getTakenByRole() {
//        return takenByRole;
//    }
//
//    public void setTakenByRole(String takenByRole) {
//        this.takenByRole = takenByRole;
//    }
//}



package com.example.stud_erp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(
        uniqueConstraints = @UniqueConstraint(
                columnNames = {
                        "student_id",
                        "classNumber",
                        "date"
                }
        )
)
public class StuAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ================= SCHOOL =================
    private Long schoolId;

    private String schoolName;

    // ================= CLASS =================
    private Long classId;

    private String className;

    // ================= DATE =================
    private LocalDate date;

    // P / A
    private String status;

    // ================= STUDENT =================
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    // ================= TAKEN BY =================
    private Long takenById;

    private String takenByName;

    // HOD / TEACHER / SCHOOL_ADMIN
    private String takenByRole;


// GETTERS & SETTERS


    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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
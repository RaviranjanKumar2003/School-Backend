//package com.example.stud_erp.entity;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import jakarta.persistence.*;
//
//import java.time.LocalDate;
//
//@Entity
//@Table(name = "attendance")
//public class Attendance {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String status; // P / A
//
//    @ManyToOne
//    @JoinColumn(name = "class_session_id")
//    @JsonBackReference
//    private ClassSession classSession;
//
//    @ManyToOne
//    @JoinColumn(name = "student_id", nullable = false)
//    private Student student;
//
//    private LocalDate date;
//    private int classNumber;
//
//// GETTERS & SETTERS
//
//
//    public LocalDate getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDate date) {
//        this.date = date;
//    }
//
//    public int getClassNumber() {
//        return classNumber;
//    }
//
//    public void setClassNumber(int classNumber) {
//        this.classNumber = classNumber;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
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
//    public ClassSession getClassSession() {
//        return classSession;
//    }
//
//    public void setClassSession(ClassSession classSession) {
//        this.classSession = classSession;
//    }
//
//    public Student getStudent() {
//        return student;
//    }
//
//    public void setStudent(Student student) {
//        this.student = student;
//    }
//}



// updated



//
//
//package com.example.stud_erp.entity;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import jakarta.persistence.*;
//
//import java.time.LocalDate;
//
//@Entity
//@Table(
//        name = "attendance",
//        uniqueConstraints = {
//                // ✅ PREVENT DUPLICATE ATTENDANCE
//                @UniqueConstraint(columnNames = {"student_id", "date", "classNumber"})
//        }
//)
//public class Attendance {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    // ✅ USE ENUM FOR SAFETY
//    @Enumerated(EnumType.STRING)
//    private AttendanceStatus status; // P / A
//
//    @ManyToOne
//    @JoinColumn(name = "class_session_id")
//    @JsonBackReference
//    private ClassSession classSession;
//
//    @ManyToOne
//    @JoinColumn(name = "student_id", nullable = false)
//    private Student student;
//
//    private LocalDate date;
//
//    private int classNumber;
//
//    // ================= GETTERS & SETTERS =================
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public AttendanceStatus getStatus() {
//        return status;
//    }
//
//    public void setStatus(AttendanceStatus status) {
//        this.status = status;
//    }
//
//    public ClassSession getClassSession() {
//        return classSession;
//    }
//
//    public void setClassSession(ClassSession classSession) {
//        this.classSession = classSession;
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
//    public LocalDate getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDate date) {
//        this.date = date;
//    }
//
//    public int getClassNumber() {
//        return classNumber;
//    }
//
//    public void setClassNumber(int classNumber) {
//        this.classNumber = classNumber;
//    }
//}



// update 06/05/26


//
//package com.example.stud_erp.entity;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import jakarta.persistence.*;
//
//import java.time.LocalDate;
//
//@Entity
//@Table(
//        name = "attendance",
//        uniqueConstraints = {
//                @UniqueConstraint(columnNames = {"student_id", "date", "classNumber"})
//        }
//)
//public class Attendance {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private AttendanceStatus status;
//
//    @ManyToOne
//    @JoinColumn(name = "class_session_id")
//    @JsonBackReference
//    private ClassSession classSession;
//
//    @ManyToOne
//    @JoinColumn(name = "student_id", nullable = false)
//    private Student student;
//
//    @Column(nullable = false)
//    private LocalDate date;
//
//    @Column(nullable = false)
//    private int classNumber;
//
//    // ================= GETTERS & SETTERS =================
//
//    public Long getId() {
//        return id;
//    }
//
//    public AttendanceStatus getStatus() {
//        return status;
//    }
//
//    public void setStatus(AttendanceStatus status) {
//        this.status = status;
//    }
//
//    public ClassSession getClassSession() {
//        return classSession;
//    }
//
//    public void setClassSession(ClassSession classSession) {
//        this.classSession = classSession;
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
//    public LocalDate getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDate date) {
//        this.date = date;
//    }
//
//    public int getClassNumber() {
//        return classNumber;
//    }
//
//    public void setClassNumber(int classNumber) {
//        this.classNumber = classNumber;
//    }
//}


// update 07/05/26


package com.example.stud_erp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(
        name = "attendance",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {
                                "student_id",
                                "attendance_date",
                                "class_name"
                        }
                )
        }
)
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ======================================
    // ATTENDANCE STATUS (FIXED: Enum to String)
    // ======================================
    @Column(nullable = false)
    private String status; // Yahan String kar diya hai

    @ManyToOne
    @JoinColumn(name = "class_session_id")
    @JsonBackReference
    private ClassSession classSession;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(name = "attendance_date", nullable = false)
    private LocalDate attendanceDate;

    @Column(name = "class_name", nullable = false)
    private String className;

    // ======================================
    // GETTERS & SETTERS (FIXED)
    // ======================================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // STATUS (Ab String return karega)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ClassSession getClassSession() {
        return classSession;
    }

    public void setClassSession(ClassSession classSession) {
        this.classSession = classSession;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
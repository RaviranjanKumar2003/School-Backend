//package com.example.stud_erp.entity;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//import java.time.LocalDate;
//
//@Entity
//@Data
<<<<<<< HEAD
//@Table(
//        uniqueConstraints = @UniqueConstraint(
//                columnNames = {"student_id", "classNumber", "date"}
//        )
//)
=======
>>>>>>> 5bf6a9a (work done)
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
<<<<<<< HEAD
//    // ================= NEW FIELDS 🔥 =================
//
//    private Long takenById;        // HOD ya Teacher ID
//
//    private String takenByName;   // Name store kar lo (fast access)
//
//    private String takenByRole;   // "HOD" / "TEACHER"
//
=======
>>>>>>> 5bf6a9a (work done)
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
<<<<<<< HEAD
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
=======
//}


// updated


//
//package com.example.stud_erp.entity;
//
//import jakarta.persistence.*;
//
//import java.time.LocalDate;
//
//@Entity
//@Table(
//        name = "stu_attendance",
//        uniqueConstraints = {
//                // 🔥 ONE STUDENT → ONE DATE → ONE RECORD
//                @UniqueConstraint(columnNames = {"student_id", "date", "classNumber"})
//        }
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
//    // ✅ SAFE ENUM
//    @Enumerated(EnumType.STRING)
//    private AttendanceStatus status;
//
//    @ManyToOne
//    @JoinColumn(name = "student_id", nullable = false)
//    private Student student;
//
//    // ================= GETTERS & SETTERS =================
//
//    public Long getId() {
//        return id;
//    }
//
//    public Integer getClassNumber() {
//        return classNumber;
//    }
//
//    public LocalDate getDate() {
//        return date;
//    }
//
//    public AttendanceStatus getStatus() {
//        return status;
//    }
//
//    public Student getStudent() {
//        return student;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setClassNumber(Integer classNumber) {
//        this.classNumber = classNumber;
//    }
//
//    public void setDate(LocalDate date) {
//        this.date = date;
//    }
//
//    public void setStatus(AttendanceStatus status) {
//        this.status = status;
//    }
//
//    public void setStudent(Student student) {
//        this.student = student;
>>>>>>> 5bf6a9a (work done)
//    }
//}



<<<<<<< HEAD
=======
//update 07/05/26

//package com.example.stud_erp.entity;
//
//import jakarta.persistence.*;
//import java.time.LocalDate;
//
//@Entity
//@Table(
//        name = "stu_attendance",
//        uniqueConstraints = {
//                @UniqueConstraint(
//                        columnNames = {"student_id", "attendance_date", "class_name"}
//                )
//        }
//)
//public class StuAttendance {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "class_name", nullable = false)
//    private String className;
//
//    @Column(name = "attendance_date", nullable = false)
//    private LocalDate attendanceDate;
//
//    // ✅ FIX: Enum hata kar String kar diya hai
//    @Column(nullable = false)
//    private String status;
//
//    @ManyToOne
//    @JoinColumn(name = "student_id", nullable = false)
//    private Student student;
//
//    // GETTERS & SETTERS
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public String getClassName() { return className; }
//    public void setClassName(String className) { this.className = className; }
//
//    public LocalDate getAttendanceDate() { return attendanceDate; }
//    public void setAttendanceDate(LocalDate attendanceDate) { this.attendanceDate = attendanceDate; }
//
//    // ✅ FIX: Getter/Setter ab String return karenge
//    public String getStatus() { return status; }
//    public void setStatus(String status) { this.status = status; }
//
//    public Student getStudent() { return student; }
//    public void setStudent(Student student) { this.student = student; }
//}










>>>>>>> 5bf6a9a (work done)
package com.example.stud_erp.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
<<<<<<< HEAD
@Data
@Table(
        uniqueConstraints = @UniqueConstraint(
                columnNames = {
                        "student_id",
                        "classNumber",
                        "date"
                }
        )
=======
@Table(
        name = "stu_attendance",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {
                                "student_id",
                                "attendance_date",
                                "class_name"
                        }
                )
        }
>>>>>>> 5bf6a9a (work done)
)
public class StuAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

<<<<<<< HEAD
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
=======
    // =========================================
    // CLASS DETAILS
    // =========================================
    @Column(name = "class_name", nullable = false)
    private String className;

    // =========================================
    // STUDENT DETAILS
    // =========================================
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(name = "student_name")
    private String studentName;
>>>>>>> 5bf6a9a (work done)

    // =========================================
    // ATTENDANCE DETAILS
    // =========================================
    @Column(name = "attendance_date", nullable = false)
    private LocalDate attendanceDate;

    @Column(name = "attendance_time")
    private LocalTime attendanceTime;

    @Column(nullable = false)
    private String status;

    // =========================================
    // PROFESSOR DETAILS
    // =========================================
    @Column(name = "professor_name")
    private String professorName;

    // =========================================
    // SUBJECT
    // =========================================
    @Column(name = "subject_name")
    private String subjectName;

    // =========================================
    // GETTERS & SETTERS
    // =========================================

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

    public String getClassName() {
        return className;
    }

=======
    public String getClassName() {
        return className;
    }

>>>>>>> 5bf6a9a (work done)
    public void setClassName(String className) {
        this.className = className;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public LocalTime getAttendanceTime() {
        return attendanceTime;
    }

    public void setAttendanceTime(LocalTime attendanceTime) {
        this.attendanceTime = attendanceTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
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
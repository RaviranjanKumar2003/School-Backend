package com.example.stud_erp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(
        name = "students",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "studentId"),
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        }
)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ================= SCHOOL INFO =================
    private Long schoolId;
    private String schoolCode;
    private String schoolName;

    // ================= STUDENT INFO =================
    private String studentId;
    private String username;
    private String password;
    private String email;

    private Long classNumber;
    private String className;

    private Long studRollNo;
    private String studName;
    private String studFatherName;
    private String studLastName;
    private String studPhoneNumber;

    private LocalDate studentDob;
    private String studCategory;
    private String studCaste;
    private int studentAge;

    private String imageUrl;

    private boolean isDeleted = false;

    private String otp;
    private LocalDateTime otpExpiry;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Attendance> attendance;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }



// GETTERS & SETTERS


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

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(Long classNumber) {
        this.classNumber = classNumber;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Long getStudRollNo() {
        return studRollNo;
    }

    public void setStudRollNo(Long studRollNo) {
        this.studRollNo = studRollNo;
    }

    public String getStudName() {
        return studName;
    }

    public void setStudName(String studName) {
        this.studName = studName;
    }

    public String getStudFatherName() {
        return studFatherName;
    }

    public void setStudFatherName(String studFatherName) {
        this.studFatherName = studFatherName;
    }

    public String getStudLastName() {
        return studLastName;
    }

    public void setStudLastName(String studLastName) {
        this.studLastName = studLastName;
    }

    public String getStudPhoneNumber() {
        return studPhoneNumber;
    }

    public void setStudPhoneNumber(String studPhoneNumber) {
        this.studPhoneNumber = studPhoneNumber;
    }

    public LocalDate getStudentDob() {
        return studentDob;
    }

    public void setStudentDob(LocalDate studentDob) {
        this.studentDob = studentDob;
    }

    public String getStudCategory() {
        return studCategory;
    }

    public void setStudCategory(String studCategory) {
        this.studCategory = studCategory;
    }

    public String getStudCaste() {
        return studCaste;
    }

    public void setStudCaste(String studCaste) {
        this.studCaste = studCaste;
    }

    public int getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(int studentAge) {
        this.studentAge = studentAge;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalDateTime getOtpExpiry() {
        return otpExpiry;
    }

    public void setOtpExpiry(LocalDateTime otpExpiry) {
        this.otpExpiry = otpExpiry;
    }

    public Set<Attendance> getAttendance() {
        return attendance;
    }

    public void setAttendance(Set<Attendance> attendance) {
        this.attendance = attendance;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
package com.example.stud_erp.payload;

import java.time.LocalDate;

public class StudentDTO {

    private Long id;

    // ================= SCHOOL =================
    private Long schoolId;
    private String schoolCode;
    private String schoolName;

    // ================= STUDENT =================
    private String studentId;
    private String username;
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

    // optional
    private StudentFeeDTO fee;



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

    public StudentFeeDTO getFee() {
        return fee;
    }

    public void setFee(StudentFeeDTO fee) {
        this.fee = fee;
    }
}
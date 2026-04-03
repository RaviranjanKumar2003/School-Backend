//package com.example.stud_erp.entity;
//
//import jakarta.persistence.*;
//import java.time.LocalDate;
//
//@Entity
//@Table(name = "student_fees")
//public class StudentFee {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    // Student Details
//    @Column(nullable = false)
//    private Long studentId;
//
//    @Column(nullable = false)
//    private String studentName;
//
//    @Column(nullable = false)
//    private String className;
//
//    // Fee Details
//    @Column(nullable = false)
//    private Double totalFee;
//
//    @Column(nullable = false)
//    private Double paidAmount;
//
//    private Double pendingAmount;
//
//    @Column(nullable = false)
//    private String status; // PAID / PENDING
//
//    private LocalDate paymentDate;
//
//    private String paymentMode; // CASH / ONLINE / UPI
//
//    private String remark;
//
//    // =======================
//    // 🔥 AUTO CALCULATION LOGIC
//    // =======================
//    @PrePersist
//    @PreUpdate
//    public void calculateFeeStatus() {
//        if (paidAmount == null) paidAmount = 0.0;
//        if (totalFee == null) totalFee = 0.0;
//
//        this.pendingAmount = totalFee - paidAmount;
//
//        if (paidAmount >= totalFee) {
//            this.status = "PAID";
//            this.pendingAmount = 0.0;
//        } else {
//            this.status = "PENDING";
//        }
//    }
//
//    // =======================
//    // GETTERS & SETTERS
//    // =======================
//
//    public Long getId() {
//        return id;
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
//    public String getClassName() {
//        return className;
//    }
//
//    public void setClassName(String className) {
//        this.className = className;
//    }
//
//    public Double getTotalFee() {
//        return totalFee;
//    }
//
//    public void setTotalFee(Double totalFee) {
//        this.totalFee = totalFee;
//    }
//
//    public Double getPaidAmount() {
//        return paidAmount;
//    }
//
//    public void setPaidAmount(Double paidAmount) {
//        this.paidAmount = paidAmount;
//    }
//
//    public Double getPendingAmount() {
//        return pendingAmount;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public LocalDate getPaymentDate() {
//        return paymentDate;
//    }
//
//    public void setPaymentDate(LocalDate paymentDate) {
//        this.paymentDate = paymentDate;
//    }
//
//    public String getPaymentMode() {
//        return paymentMode;
//    }
//
//    public void setPaymentMode(String paymentMode) {
//        this.paymentMode = paymentMode;
//    }
//
//    public String getRemark() {
//        return remark;
//    }
//
//    public void setRemark(String remark) {
//        this.remark = remark;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setPendingAmount(Double pendingAmount) {
//        this.pendingAmount = pendingAmount;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//}



package com.example.stud_erp.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "student_fees")
public class StudentFee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;
    private String studentName;
    private String className;

    private String month;
    private int year; // 🔥 NEW FIELD

    private Double totalFee;
    private Double paidAmount;
    private Double pendingAmount;

    private String status;

    private LocalDate paymentDate;
    private String paymentMode;
    private String remark;

    @PrePersist
    @PreUpdate
    public void calculateFee() {
        if (paidAmount == null) paidAmount = 0.0;
        if (totalFee == null) totalFee = 0.0;

        pendingAmount = totalFee - paidAmount;

        if (pendingAmount <= 0) {
            status = "PAID";
            pendingAmount = 0.0;
        } else {
            status = "PENDING";
        }
    }

    // getters setters (same as before)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Double getPendingAmount() {
        return pendingAmount;
    }

    public void setPendingAmount(Double pendingAmount) {
        this.pendingAmount = pendingAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
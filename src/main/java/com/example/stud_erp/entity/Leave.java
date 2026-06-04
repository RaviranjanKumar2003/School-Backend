package com.example.stud_erp.entity;

import com.example.stud_erp.enums.LeaveType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "leaves")
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // =====================================================
    // LEAVE DETAILS
    // =====================================================

    private String reason;

    private LocalDate fromDate;

    private LocalDate toDate;

    /*
       HOD
       TEACHER
     */
    private String sendTo;

    /*
       PENDING
       APPROVED
       REJECTED
     */
    private String status = "PENDING";

    // =====================================================
    // MULTI SCHOOL
    // =====================================================

    private Long schoolId;

    // =====================================================
    // TEACHER TARGET
    // =====================================================

    private Long teacherId;

    // =====================================================
    // STUDENT
    // =====================================================

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonIgnoreProperties({
            "school",
            "classEntity",
            "subjects"
    })
    private Student student;

    // =====================================================
    // TIMESTAMP
    // =====================================================

    private LocalDateTime createdAt;
    private String className;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // =====================================================
    // WHO APPROVED / REJECTED
    // =====================================================

    private Long actionById;

    private String actionByType;

      /*
       HOD
       TEACHER
       ADMIN
      */

    private String responseMessage;
    @Enumerated(EnumType.STRING)
    private LeaveType leaveType;

    // =====================================================
    // WHO APPLIED
    // =====================================================

    private String senderType;
    private Long senderId;
    // =====================================================
    // GETTERS & SETTERS
    // =====================================================


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSenderType() {
        return senderType;
    }

    public void setSenderType(String senderType) {
        this.senderType = senderType;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    public Long getId() {
        return id;
    }

    public String getReason() {
        return reason;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public String getSendTo() {
        return sendTo;
    }

    public String getStatus() {
        return status;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public Student getStudent() {
        return student;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getActionById() {
        return actionById;
    }

    public void setActionById(Long actionById) {
        this.actionById = actionById;
    }

    public String getActionByType() {
        return actionByType;
    }

    public void setActionByType(String actionByType) {
        this.actionByType = actionByType;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

}
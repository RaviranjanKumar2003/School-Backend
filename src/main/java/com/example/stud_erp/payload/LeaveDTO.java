package com.example.stud_erp.payload;

import com.example.stud_erp.enums.LeaveType;

import java.time.LocalDate;

public class LeaveDTO {

    private Long studentId;

    private String reason;

    private LocalDate fromDate;

    private LocalDate toDate;

    /*
      HOD / TEACHER
     */
    private String sendTo;

    /*
      Only if TEACHER
     */
    private Long teacherId;
    private LeaveType leaveType;
    private String senderType;

    private Long senderId;

    // ================= GETTERS =================


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

    public Long getStudentId() {
        return studentId;
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

    public Long getTeacherId() {
        return teacherId;
    }

    // ================= SETTERS =================

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
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

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }


}
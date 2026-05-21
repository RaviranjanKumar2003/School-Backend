package com.example.stud_erp.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CallLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long inquiryId;

    private String phone;

    private String callStatus;
    // CONNECTED / NOT_PICKED / BUSY / WRONG_NUMBER / SWITCHED_OFF

    private int durationInSeconds;

    @Column(length = 2000)
    private String remarks;

    private LocalDateTime callTime;

    private String calledBy; // receptionist/agent id

    @PrePersist
    public void onCreate() {
        callTime = LocalDateTime.now();
    }

// GETTERS & SETTERS

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getInquiryId() { return inquiryId; }
    public void setInquiryId(Long inquiryId) { this.inquiryId = inquiryId; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getCallStatus() { return callStatus; }
    public void setCallStatus(String callStatus) { this.callStatus = callStatus; }

    public int getDurationInSeconds() { return durationInSeconds; }
    public void setDurationInSeconds(int durationInSeconds) { this.durationInSeconds = durationInSeconds; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public LocalDateTime getCallTime() { return callTime; }
    public void setCallTime(LocalDateTime callTime) { this.callTime = callTime; }

    public String getCalledBy() { return calledBy; }
    public void setCalledBy(String calledBy) { this.calledBy = calledBy; }
}
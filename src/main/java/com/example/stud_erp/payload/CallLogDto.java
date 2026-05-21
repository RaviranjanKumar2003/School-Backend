package com.example.stud_erp.payload;

import lombok.Data;

@Data
public class CallLogDto {

    private Long inquiryId;

    private String phone;

    private String callStatus;

    private int durationInSeconds;

    private String remarks;

    private String calledBy;


    public Long getInquiryId() {
        return inquiryId;
    }

    public void setInquiryId(Long inquiryId) {
        this.inquiryId = inquiryId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCallStatus() {
        return callStatus;
    }

    public void setCallStatus(String callStatus) {
        this.callStatus = callStatus;
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(int durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCalledBy() {
        return calledBy;
    }

    public void setCalledBy(String calledBy) {
        this.calledBy = calledBy;
    }
}
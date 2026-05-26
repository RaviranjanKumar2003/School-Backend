package com.example.stud_erp.payload;

public class PeriodDto {

    private Long id;

    // =====================================================
    // SCHOOL
    // =====================================================

    private Long schoolId;

    // =====================================================
    // PERIOD
    // =====================================================

    private int periodNumber;

    private String startTime;

    private String endTime;

    // =====================================================
    // TYPE
    // =====================================================

    private String type;

    // =====================================================
    // TITLE
    // =====================================================

    private String title;

    // =====================================================
    // ACTIVE
    // =====================================================

    private Boolean active;



    // =====================================================
// GETTERS & SETTERS
    // =====================================================

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

    public int getPeriodNumber() {
        return periodNumber;
    }

    public void setPeriodNumber(int periodNumber) {
        this.periodNumber = periodNumber;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
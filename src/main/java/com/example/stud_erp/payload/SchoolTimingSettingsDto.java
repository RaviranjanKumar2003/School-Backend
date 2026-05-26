package com.example.stud_erp.payload;

public class SchoolTimingSettingsDto {

    private Long id;

    // =====================================================
    // SCHOOL
    // =====================================================

    private Long schoolId;

    // =====================================================
    // TIMING
    // =====================================================

    private int periodsPerDay;

    private int periodDuration;

    private int lunchAfterPeriod;

    private String schoolStartTime;

    private String schoolEndTime;

    // =====================================================
    // BREAKS
    // =====================================================

    private int lunchDuration;

    private int shortBreakDuration;

    // =====================================================
    // WORKING DAYS
    // =====================================================

    private String workingDays;

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

    public int getPeriodsPerDay() {
        return periodsPerDay;
    }

    public void setPeriodsPerDay(int periodsPerDay) {
        this.periodsPerDay = periodsPerDay;
    }

    public int getPeriodDuration() {
        return periodDuration;
    }

    public void setPeriodDuration(int periodDuration) {
        this.periodDuration = periodDuration;
    }

    public int getLunchAfterPeriod() {
        return lunchAfterPeriod;
    }

    public void setLunchAfterPeriod(int lunchAfterPeriod) {
        this.lunchAfterPeriod = lunchAfterPeriod;
    }

    public String getSchoolStartTime() {
        return schoolStartTime;
    }

    public void setSchoolStartTime(String schoolStartTime) {
        this.schoolStartTime = schoolStartTime;
    }

    public String getSchoolEndTime() {
        return schoolEndTime;
    }

    public void setSchoolEndTime(String schoolEndTime) {
        this.schoolEndTime = schoolEndTime;
    }

    public int getLunchDuration() {
        return lunchDuration;
    }

    public void setLunchDuration(int lunchDuration) {
        this.lunchDuration = lunchDuration;
    }

    public int getShortBreakDuration() {
        return shortBreakDuration;
    }

    public void setShortBreakDuration(int shortBreakDuration) {
        this.shortBreakDuration = shortBreakDuration;
    }

    public String getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(String workingDays) {
        this.workingDays = workingDays;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
package com.example.stud_erp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "school_timing_settings")
public class SchoolTimingSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // =====================================================
    // SCHOOL
    // =====================================================

    private Long schoolId;

    // =====================================================
    // TIMING SETTINGS
    // =====================================================

    // Example: 8 periods
    private int periodsPerDay;

    // Example: 45 minutes
    private int periodDuration;

    // Example: lunch after 4th period
    private int lunchAfterPeriod;

    // Example: 09:00
    private String schoolStartTime;

    // Example: 03:30
    private String schoolEndTime;

    // =====================================================
    // BREAK SETTINGS
    // =====================================================

    // Example: 30 minutes lunch
    private int lunchDuration;

    // Example: 5 minutes small break
    private int shortBreakDuration;

    // =====================================================
    // WEEK SETTINGS
    // =====================================================

    // Example:
    // MONDAY,TUESDAY,WEDNESDAY...
    private String workingDays;

    // =====================================================
    // ACTIVE
    // =====================================================

    private Boolean active = true;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public SchoolTimingSettings() {
    }



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
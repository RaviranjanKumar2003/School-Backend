package com.example.stud_erp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "periods")
public class Period {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // =====================================================
    // SCHOOL
    // =====================================================

    private Long schoolId;

    // =====================================================
    // PERIOD DETAILS
    // =====================================================

    // Example: 1,2,3,4
    private int periodNumber;

    // Example: 09:00
    private String startTime;

    // Example: 09:45
    private String endTime;

    // =====================================================
    // BREAK / LUNCH
    // =====================================================

    // PERIOD / LUNCH / BREAK
    private String type = "PERIOD";

    // =====================================================
    // DISPLAY NAME
    // Example:
    // Period 1
    // Lunch Break
    // =====================================================

    private String title;

    // =====================================================
    // ACTIVE
    // =====================================================

    private Boolean active = true;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public Period() {
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
package com.example.stud_erp.payload;

import java.time.LocalDate;
import java.time.LocalTime;

public class LiveClassDto {

    // ================= BASIC INFO =================

    private String topic;

    private String description;

    // ================= MEETING =================

    private String meetingProvider;

    private String meetingLink;

    private String meetingId;

    private String meetingPassword;

    // ================= RELATIONS =================

    private Long schoolId;

    private Long classId;

    private Long professorId;

    // ================= SCHEDULE =================

    private LocalDate scheduledDate;

    private LocalTime scheduledTime;

    // ================= SETTINGS =================

    private Boolean recordingEnabled = false;

    private Boolean notifyStudents = true;

    private Boolean notifyParents = false;

    // ================= GETTERS & SETTERS =================

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMeetingProvider() {
        return meetingProvider;
    }

    public void setMeetingProvider(String meetingProvider) {
        this.meetingProvider = meetingProvider;
    }

    public String getMeetingLink() {
        return meetingLink;
    }

    public void setMeetingLink(String meetingLink) {
        this.meetingLink = meetingLink;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    public String getMeetingPassword() {
        return meetingPassword;
    }

    public void setMeetingPassword(String meetingPassword) {
        this.meetingPassword = meetingPassword;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public LocalTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(LocalTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public Boolean getRecordingEnabled() {
        return recordingEnabled;
    }

    public void setRecordingEnabled(Boolean recordingEnabled) {
        this.recordingEnabled = recordingEnabled;
    }

    public Boolean getNotifyStudents() {
        return notifyStudents;
    }

    public void setNotifyStudents(Boolean notifyStudents) {
        this.notifyStudents = notifyStudents;
    }

    public Boolean getNotifyParents() {
        return notifyParents;
    }

    public void setNotifyParents(Boolean notifyParents) {
        this.notifyParents = notifyParents;
    }
}
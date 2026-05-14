package com.example.stud_erp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "live_sessions")
public class LiveSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ================= BASIC INFO =================

    @Column(nullable = false)
    private String topic;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false)
    private String professorName;

    @Column(nullable = false)
    private String className;

    // ================= MEETING =================

    @Column(nullable = false, length = 2000)
    private String meetingLink;

    // ================= STATUS =================

    private boolean active = true;

    private boolean ended = false;

    // ================= DATE & TIME =================

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate scheduledDate;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime scheduledTime;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    // ================= PARTICIPANTS =================

    private Integer participants = 0;

    // ================= AUTO TIME =================

    @PrePersist
    public void onCreate() {

        if (this.startedAt == null) {
            this.startedAt = LocalDateTime.now();
        }

        if (this.participants == null) {
            this.participants = 0;
        }
    }

    // ================= CONSTRUCTORS =================

    public LiveSession() {
    }

    public LiveSession(Long id,
                       String topic,
                       String description,
                       String professorName,
                       String className,
                       String meetingLink,
                       boolean active,
                       boolean ended,
                       LocalDate scheduledDate,
                       LocalTime scheduledTime,
                       LocalDateTime startedAt,
                       LocalDateTime endedAt,
                       Integer participants) {

        this.id = id;
        this.topic = topic;
        this.description = description;
        this.professorName = professorName;
        this.className = className;
        this.meetingLink = meetingLink;
        this.active = active;
        this.ended = ended;
        this.scheduledDate = scheduledDate;
        this.scheduledTime = scheduledTime;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.participants = participants;
    }

    // ================= GETTERS & SETTERS =================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMeetingLink() {
        return meetingLink;
    }

    public void setMeetingLink(String meetingLink) {
        this.meetingLink = meetingLink;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isEnded() {
        return ended;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
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

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(LocalDateTime endedAt) {
        this.endedAt = endedAt;
    }

    public Integer getParticipants() {
        return participants;
    }

    public void setParticipants(Integer participants) {
        this.participants = participants;
    }
}
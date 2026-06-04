package com.example.stud_erp.payload;

import java.time.LocalDate;
import java.time.LocalTime;

public class LiveClassResponse {
    public Long id;
    public String topic;
    public String description;
    public String status;
    public String meetingLink;
    public String meetingProvider;
    public LocalDate scheduledDate;
    public LocalTime scheduledTime;
    public Integer totalParticipants;
    public Integer totalAttendance;
    public Integer totalMessages;
    public String className;

    public Integer currentParticipants;
}
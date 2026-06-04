package com.example.stud_erp.service;

import com.example.stud_erp.payload.LiveClassAttendanceDto;

import java.util.List;

public interface LiveClassAttendanceService {

    LiveClassAttendanceDto joinClass(
            Long liveClassId,
            Long studentId
    );

    LiveClassAttendanceDto leaveClass(
            Long liveClassId,
            Long studentId
    );

    List<LiveClassAttendanceDto> getAttendanceByClass(
            Long liveClassId
    );

    Long getAttendanceCount(
            Long liveClassId
    );
}
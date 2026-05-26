package com.example.stud_erp.service;

import com.example.stud_erp.payload.SchoolStatisticsDto;

public interface SchoolStatisticsService {

    SchoolStatisticsDto createStatistics(
            SchoolStatisticsDto dto
    );

    SchoolStatisticsDto updateStatistics(
            Long id,
            SchoolStatisticsDto dto
    );

    SchoolStatisticsDto getStatisticsById(
            Long id
    );

    SchoolStatisticsDto getStatisticsBySchool(
            Long schoolId
    );

    void deleteStatistics(
            Long id
    );
}
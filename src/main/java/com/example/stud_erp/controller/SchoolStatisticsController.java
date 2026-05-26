package com.example.stud_erp.controller;

import com.example.stud_erp.payload.SchoolStatisticsDto;
import com.example.stud_erp.service.SchoolStatisticsService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/statistics")
@CrossOrigin("*")
public class SchoolStatisticsController {

    private final SchoolStatisticsService statisticsService;

    public SchoolStatisticsController(
            SchoolStatisticsService statisticsService
    ) {

        this.statisticsService =
                statisticsService;
    }

    // =====================================================
    // CREATE
    // =====================================================

    @PostMapping
    public SchoolStatisticsDto createStatistics(

            @RequestBody
            SchoolStatisticsDto dto

    ) {

        return statisticsService
                .createStatistics(dto);
    }

    // =====================================================
    // UPDATE
    // =====================================================

    @PutMapping("/{id}")
    public SchoolStatisticsDto updateStatistics(

            @PathVariable Long id,

            @RequestBody
            SchoolStatisticsDto dto

    ) {

        return statisticsService
                .updateStatistics(
                        id,
                        dto
                );
    }

    // =====================================================
    // GET BY ID
    // =====================================================

    @GetMapping("/{id}")
    public SchoolStatisticsDto getStatisticsById(

            @PathVariable Long id

    ) {

        return statisticsService
                .getStatisticsById(id);
    }

    // =====================================================
    // GET BY SCHOOL
    // =====================================================

    @GetMapping("/school/{schoolId}")
    public SchoolStatisticsDto getStatisticsBySchool(

            @PathVariable Long schoolId

    ) {

        return statisticsService
                .getStatisticsBySchool(
                        schoolId
                );
    }

    // =====================================================
    // DELETE
    // =====================================================

    @DeleteMapping("/{id}")
    public String deleteStatistics(

            @PathVariable Long id

    ) {

        statisticsService.deleteStatistics(id);

        return "Statistics deleted successfully";
    }
}
package com.example.stud_erp.service.Implementation;

import com.example.stud_erp.entity.School;
import com.example.stud_erp.entity.SchoolStatistics;
import com.example.stud_erp.payload.SchoolStatisticsDto;
import com.example.stud_erp.repository.SchoolRepository;
import com.example.stud_erp.repository.SchoolStatisticsRepository;
import com.example.stud_erp.service.SchoolStatisticsService;

import org.springframework.stereotype.Service;

@Service
public class SchoolStatisticsServiceImpl
        implements SchoolStatisticsService {

    private final SchoolStatisticsRepository statisticsRepository;

    private final SchoolRepository schoolRepository;

    public SchoolStatisticsServiceImpl(
            SchoolStatisticsRepository statisticsRepository,
            SchoolRepository schoolRepository
    ) {

        this.statisticsRepository =
                statisticsRepository;

        this.schoolRepository =
                schoolRepository;
    }

    // =====================================================
    // CREATE
    // =====================================================

    @Override
    public SchoolStatisticsDto createStatistics(
            SchoolStatisticsDto dto
    ) {

        School school =
                schoolRepository.findById(
                        dto.getSchoolId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "School not found"
                        )
                );

        SchoolStatistics statistics =
                new SchoolStatistics();

        setData(statistics, dto);

        statistics.setSchool(school);

        return mapToDto(
                statisticsRepository.save(statistics)
        );
    }

    // =====================================================
    // UPDATE
    // =====================================================

    @Override
    public SchoolStatisticsDto updateStatistics(
            Long id,
            SchoolStatisticsDto dto
    ) {

        SchoolStatistics statistics =
                statisticsRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Statistics not found"
                                )
                        );

        setData(statistics, dto);

        return mapToDto(
                statisticsRepository.save(statistics)
        );
    }

    // =====================================================
    // GET BY ID
    // =====================================================

    @Override
    public SchoolStatisticsDto getStatisticsById(
            Long id
    ) {

        SchoolStatistics statistics =
                statisticsRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Statistics not found"
                                )
                        );

        return mapToDto(statistics);
    }

    // =====================================================
    // GET BY SCHOOL
    // =====================================================

    @Override
    public SchoolStatisticsDto getStatisticsBySchool(
            Long schoolId
    ) {

        SchoolStatistics statistics =
                statisticsRepository.findBySchoolId(
                        schoolId
                );

        if (statistics == null) {

            throw new RuntimeException(
                    "Statistics not found"
            );
        }

        return mapToDto(statistics);
    }

    // =====================================================
    // DELETE
    // =====================================================

    @Override
    public void deleteStatistics(
            Long id
    ) {

        statisticsRepository.deleteById(id);
    }

    // =====================================================
    // SET DATA
    // =====================================================

    private void setData(
            SchoolStatistics statistics,
            SchoolStatisticsDto dto
    ) {

        statistics.setTotalStudents(
                dto.getTotalStudents()
        );

        statistics.setBoysCount(
                dto.getBoysCount()
        );

        statistics.setGirlsCount(
                dto.getGirlsCount()
        );

        statistics.setTotalTeachers(
                dto.getTotalTeachers()
        );

        statistics.setTotalStaff(
                dto.getTotalStaff()
        );

        statistics.setTotalClasses(
                dto.getTotalClasses()
        );

        statistics.setTotalSections(
                dto.getTotalSections()
        );

        statistics.setTotalClassrooms(
                dto.getTotalClassrooms()
        );

        statistics.setTotalLabs(
                dto.getTotalLabs()
        );

        statistics.setTotalLibraries(
                dto.getTotalLibraries()
        );

        statistics.setTotalComputers(
                dto.getTotalComputers()
        );

        statistics.setTotalBuses(
                dto.getTotalBuses()
        );

        statistics.setBoardResultPercentage(
                dto.getBoardResultPercentage()
        );

        statistics.setYearsOfExperience(
                dto.getYearsOfExperience()
        );
    }

    // =====================================================
    // DTO MAPPER
    // =====================================================

    private SchoolStatisticsDto mapToDto(
            SchoolStatistics statistics
    ) {

        SchoolStatisticsDto dto =
                new SchoolStatisticsDto();

        dto.setId(statistics.getId());

        dto.setTotalStudents(
                statistics.getTotalStudents()
        );

        dto.setBoysCount(
                statistics.getBoysCount()
        );

        dto.setGirlsCount(
                statistics.getGirlsCount()
        );

        dto.setTotalTeachers(
                statistics.getTotalTeachers()
        );

        dto.setTotalStaff(
                statistics.getTotalStaff()
        );

        dto.setTotalClasses(
                statistics.getTotalClasses()
        );

        dto.setTotalSections(
                statistics.getTotalSections()
        );

        dto.setTotalClassrooms(
                statistics.getTotalClassrooms()
        );

        dto.setTotalLabs(
                statistics.getTotalLabs()
        );

        dto.setTotalLibraries(
                statistics.getTotalLibraries()
        );

        dto.setTotalComputers(
                statistics.getTotalComputers()
        );

        dto.setTotalBuses(
                statistics.getTotalBuses()
        );

        dto.setBoardResultPercentage(
                statistics.getBoardResultPercentage()
        );

        dto.setYearsOfExperience(
                statistics.getYearsOfExperience()
        );

        if (statistics.getSchool() != null) {

            dto.setSchoolId(
                    statistics.getSchool().getId()
            );

            dto.setSchoolName(
                    statistics.getSchool().getSchoolName()
            );
        }

        return dto;
    }
}
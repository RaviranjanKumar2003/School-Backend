package com.example.stud_erp.service.Implementation;

import com.example.stud_erp.entity.SchoolTimingSettings;
import com.example.stud_erp.payload.SchoolTimingSettingsDto;
import com.example.stud_erp.repository.SchoolTimingSettingsRepo;
import com.example.stud_erp.service.SchoolTimingSettingsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolTimingSettingsServiceImpl
        implements SchoolTimingSettingsService {

    @Autowired
    private SchoolTimingSettingsRepo repo;

    // =====================================================
    // SAVE SETTINGS
    // =====================================================

    @Override
    public SchoolTimingSettingsDto saveSettings(
            SchoolTimingSettingsDto dto
    ) {

        SchoolTimingSettings settings =
                repo.findBySchoolId(dto.getSchoolId())
                        .orElse(new SchoolTimingSettings());

        settings.setSchoolId(dto.getSchoolId());

        settings.setPeriodsPerDay(dto.getPeriodsPerDay());

        settings.setPeriodDuration(dto.getPeriodDuration());

        settings.setLunchAfterPeriod(dto.getLunchAfterPeriod());

        settings.setSchoolStartTime(dto.getSchoolStartTime());

        settings.setSchoolEndTime(dto.getSchoolEndTime());

        settings.setLunchDuration(dto.getLunchDuration());

        settings.setShortBreakDuration(dto.getShortBreakDuration());

        settings.setWorkingDays(dto.getWorkingDays());

        settings.setActive(
                dto.getActive() != null
                        ? dto.getActive()
                        : true
        );

        SchoolTimingSettings saved =
                repo.save(settings);

        return mapToDto(saved);
    }

    // =====================================================
    // GET BY SCHOOL
    // =====================================================

    @Override
    public SchoolTimingSettingsDto getBySchoolId(
            Long schoolId
    ) {

        SchoolTimingSettings settings =
                repo.findBySchoolId(schoolId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Settings not found"
                                ));

        return mapToDto(settings);
    }

    // =====================================================
    // DELETE
    // =====================================================

    @Override
    public void deleteSettings(Long id) {

        repo.deleteById(id);
    }

    // =====================================================
    // DTO MAPPER
    // =====================================================

    private SchoolTimingSettingsDto mapToDto(
            SchoolTimingSettings settings
    ) {

        SchoolTimingSettingsDto dto =
                new SchoolTimingSettingsDto();

        dto.setId(settings.getId());

        dto.setSchoolId(settings.getSchoolId());

        dto.setPeriodsPerDay(
                settings.getPeriodsPerDay()
        );

        dto.setPeriodDuration(
                settings.getPeriodDuration()
        );

        dto.setLunchAfterPeriod(
                settings.getLunchAfterPeriod()
        );

        dto.setSchoolStartTime(
                settings.getSchoolStartTime()
        );

        dto.setSchoolEndTime(
                settings.getSchoolEndTime()
        );

        dto.setLunchDuration(
                settings.getLunchDuration()
        );

        dto.setShortBreakDuration(
                settings.getShortBreakDuration()
        );

        dto.setWorkingDays(
                settings.getWorkingDays()
        );

        dto.setActive(
                settings.getActive()
        );

        return dto;
    }
}
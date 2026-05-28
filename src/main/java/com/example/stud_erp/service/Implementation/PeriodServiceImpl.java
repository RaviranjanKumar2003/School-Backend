package com.example.stud_erp.service.Implementation;

import com.example.stud_erp.entity.ActivityLog;
import com.example.stud_erp.entity.Period;
import com.example.stud_erp.payload.PeriodDto;
import com.example.stud_erp.repository.ActivityLogRepository;
import com.example.stud_erp.repository.PeriodRepo;
import com.example.stud_erp.service.PeriodService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PeriodServiceImpl
        implements PeriodService {

    @Autowired
    private PeriodRepo repo;

    @Autowired
    private ActivityLogRepository activityLogRepository;

    // =====================================================
    // CREATE
    // =====================================================

    @Override
    public PeriodDto createPeriod(
            PeriodDto dto
    ) {

        Period period = new Period();

        period.setSchoolId(dto.getSchoolId());

        period.setPeriodNumber(dto.getPeriodNumber());

        period.setStartTime(dto.getStartTime());

        period.setEndTime(dto.getEndTime());

        period.setType(
                dto.getType() != null
                        ? dto.getType()
                        : "PERIOD"
        );

        period.setTitle(
                dto.getTitle() != null
                        ? dto.getTitle()
                        : "Period " + dto.getPeriodNumber()
        );

        period.setActive(
                dto.getActive() != null
                        ? dto.getActive()
                        : true
        );

        // =====================================================
        // SAVE PERIOD
        // =====================================================

        Period saved = repo.save(period);

        // =====================================================
        // ACTIVITY LOG
        // =====================================================

        try {

            ActivityLog log = new ActivityLog();

            log.setSchoolId(saved.getSchoolId());

            log.setTitle("New Period Created");

            log.setDescription(
                    saved.getTitle()
                            + " scheduled from "
                            + saved.getStartTime()
                            + " to "
                            + saved.getEndTime()
            );

            log.setType("TIMETABLE");

            log.setCreatedAt(LocalDateTime.now());

            activityLogRepository.save(log);

        } catch (Exception e) {

            System.out.println(
                    "Activity log error : "
                            + e.getMessage()
            );
        }

        return mapToDto(saved);
    }

    // =====================================================
    // GET BY SCHOOL
    // =====================================================

    @Override
    public List<PeriodDto> getBySchool(
            Long schoolId
    ) {

        return repo
                .findBySchoolIdOrderByPeriodNumberAsc(
                        schoolId
                )
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // =====================================================
    // UPDATE
    // =====================================================

    @Override
    public PeriodDto updatePeriod(
            Long id,
            PeriodDto dto
    ) {

        Period period =
                repo.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Period not found"
                                ));

        period.setPeriodNumber(dto.getPeriodNumber());

        period.setStartTime(dto.getStartTime());

        period.setEndTime(dto.getEndTime());

        period.setType(dto.getType());

        period.setTitle(dto.getTitle());

        period.setActive(dto.getActive());

        Period updated = repo.save(period);

        return mapToDto(updated);
    }

    // =====================================================
    // DELETE
    // =====================================================

    @Override
    public void deletePeriod(Long id) {

        repo.deleteById(id);
    }

    // =====================================================
    // DELETE BY SCHOOL
    // =====================================================

    @Override
    public void deleteBySchool(Long schoolId) {

        repo.deleteBySchoolId(schoolId);
    }

    // =====================================================
    // DTO MAPPER
    // =====================================================

    private PeriodDto mapToDto(
            Period period
    ) {

        PeriodDto dto = new PeriodDto();

        dto.setId(period.getId());

        dto.setSchoolId(period.getSchoolId());

        dto.setPeriodNumber(
                period.getPeriodNumber()
        );

        dto.setStartTime(
                period.getStartTime()
        );

        dto.setEndTime(
                period.getEndTime()
        );

        dto.setType(
                period.getType()
        );

        dto.setTitle(
                period.getTitle()
        );

        dto.setActive(
                period.getActive()
        );

        return dto;
    }
}
package com.example.stud_erp.service;

import com.example.stud_erp.entity.Result;
import com.example.stud_erp.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    // ✅ SAVE MARKS (Teacher)
    public Result saveMarks(Result r) {

        // 🔥 STATUS LOGIC
        if (r.getMarks() == 0) {
            r.setStatus("ABSENT");
        } else if (r.getMarks() < 33) {
            r.setStatus("FAIL");
        } else {
            r.setStatus("PASS");
        }

        return resultRepository.save(r);
    }

    // ✅ GET STUDENT RESULT (FULL CALCULATION)
    public List<Result> getStudentResult(Long studentId) {

        List<Result> results = resultRepository.findByStudentId(studentId);

        if (results.isEmpty()) {
            throw new RuntimeException("No result found");
        }

        int total = 0;
        boolean isFail = false;

        for (Result r : results) {

            total += r.getMarks();

            if (r.getMarks() < 33) {
                isFail = true;
            }
        }

        double percentage = (double) total / results.size();

        // 🔥 GRADE LOGIC
        String grade;
        if (percentage >= 90) grade = "A+";
        else if (percentage >= 75) grade = "A";
        else if (percentage >= 60) grade = "B";
        else if (percentage >= 50) grade = "C";
        else if (percentage >= 33) grade = "D";
        else grade = "F";

        // 🔥 UPDATE ALL SUBJECTS
        for (Result r : results) {
            r.setPercentage(percentage);
            r.setGrade(grade);

            if (isFail) {
                r.setStatus("FAIL");
            } else {
                r.setStatus("PASS");
            }
        }

        return results;
    }

    // ✅ UPDATE MARKS (Recheck Use)
    public Result updateMarks(Long studentId, String subject, int newMarks) {

        Result result = resultRepository.findByStudentId(studentId)
                .stream()
                .filter(r -> r.getSubject().equals(subject))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Result not found"));

        result.setMarks(newMarks);

        // 🔥 STATUS
        if (newMarks == 0) result.setStatus("ABSENT");
        else if (newMarks < 33) result.setStatus("FAIL");
        else result.setStatus("PASS");

        return resultRepository.save(result);
    }

    // ✅ GET ALL RESULTS (Admin)
    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }
}
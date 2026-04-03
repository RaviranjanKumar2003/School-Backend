package com.example.stud_erp.controller;

import com.example.stud_erp.repository.StudentRepository;
import com.example.stud_erp.repository.ProfessorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin
public class DashboardController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProfessorRepository professorRepository;


    // ================= DASHBOARD CARDS =================

    @GetMapping("/cards")
    public List<Map<String, Object>> getCards() {

        List<Map<String, Object>> cards = new ArrayList<>();

        long studentCount = studentRepository.count();
        long professorCount = professorRepository.count();

        Map<String, Object> studentCard = new HashMap<>();
        studentCard.put("title", "Total Students");
        studentCard.put("value", studentCount);

        Map<String, Object> studentFooter = new HashMap<>();
        studentFooter.put("color", "text-green-500");
        studentFooter.put("value", "+");
        studentFooter.put("label", "Students registered");

        studentCard.put("footer", studentFooter);

        Map<String, Object> professorCard = new HashMap<>();
        professorCard.put("title", "Total Professors");
        professorCard.put("value", professorCount);

        Map<String, Object> professorFooter = new HashMap<>();
        professorFooter.put("color", "text-blue-500");
        professorFooter.put("value", "+");
        professorFooter.put("label", "Teachers available");

        professorCard.put("footer", professorFooter);

        cards.add(studentCard);
        cards.add(professorCard);

        return cards;
    }


    // ================= DASHBOARD CHART =================

    @GetMapping("/charts")
    public List<Map<String, Object>> getCharts() {

        List<Map<String, Object>> charts = new ArrayList<>();

        long students = studentRepository.count();
        long professors = professorRepository.count();

        Map<String, Object> chartWrapper = new HashMap<>();

        chartWrapper.put("title", "Students vs Professors");
        chartWrapper.put("description", "Live data from database");

        Map<String, Object> chart = new HashMap<>();

        chart.put("type", "bar");
        chart.put("height", 220);

        List<Map<String, Object>> series = new ArrayList<>();

        Map<String, Object> seriesData = new HashMap<>();
        seriesData.put("name", "Total");
        seriesData.put("data", Arrays.asList(students, professors));

        series.add(seriesData);

        chart.put("series", series);

        Map<String, Object> options = new HashMap<>();

        Map<String, Object> chartOptions = new HashMap<>();
        chartOptions.put("toolbar", Map.of("show", false));

        options.put("chart", chartOptions);

        Map<String, Object> xaxis = new HashMap<>();
        xaxis.put("categories", Arrays.asList("Students", "Professors"));

        options.put("xaxis", xaxis);

        chart.put("options", options);

        chartWrapper.put("chart", chart);
        chartWrapper.put("footer", "Updated from database");

        charts.add(chartWrapper);

        return charts;
    }


    // ================= SUBJECT TABLE =================

    @GetMapping("/subjects")
    public List<Map<String, Object>> getSubjects() {

        List<Map<String, Object>> subjects = new ArrayList<>();

        professorRepository.findAll().forEach(professor -> {

            Map<String, Object> subject = new HashMap<>();

            subject.put("img", professor.getImageUrl());
            subject.put("name", professor.getSubject());
            subject.put("budget", "30 Lectures");
            subject.put("completion", 80);

            List<Map<String, String>> members = new ArrayList<>();

            Map<String, String> member = new HashMap<>();
            member.put("img", professor.getImageUrl());
            member.put("name", professor.getName());

            members.add(member);

            subject.put("members", members);

            subjects.add(subject);
        });

        return subjects;
    }


    // ================= ORDERS OVERVIEW =================

    @GetMapping("/orders")
    public List<Map<String, Object>> getOrders() {

        List<Map<String, Object>> orders = new ArrayList<>();

        long studentCount = studentRepository.count();

        Map<String, Object> order = new HashMap<>();

        order.put("color", "text-green-500");
        order.put("title", "Total Students Registered");
        order.put("description", studentCount + " Students");

        orders.add(order);

        return orders;
    }

}
package com.example.stud_erp.service;

import com.example.stud_erp.entity.ClassSession;

import java.util.List;

public interface ClassSessionService {

    // 🔥 CREATE SESSION
    ClassSession createSession(ClassSession session);

    // 🔥 GET ALL
    List<ClassSession> getAllSessions();

    // 🔥 GET BY CLASS NAME ✅ UPDATED
    List<ClassSession> getByClass(String className);

    // 🔥 DELETE
    void deleteSession(Long id);
}
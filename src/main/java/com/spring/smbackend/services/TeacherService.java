package com.spring.smbackend.services;

import com.spring.smbackend.entities.Teacher;

import java.util.List;

public interface TeacherService {

    void createTeacher(Teacher teacher);

    List<Teacher> findAll();

    Teacher findTeacherById(Long id);

    List<Teacher> findTeacherByName(String name);

    void updateTeacher(Teacher teacher);

    void deleteTeacher(Long id);
}
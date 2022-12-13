package com.spring.smbackend.services;

import com.spring.smbackend.entities.Teacher;
import com.spring.smbackend.models.TeacherDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TeacherService {

    ResponseEntity<Teacher> createTeacher(TeacherDto teacherDto);

    ResponseEntity<List<Teacher>> findAll();

    ResponseEntity<Teacher> findTeacherById(Long id);

    ResponseEntity<Teacher> updateTeacher(TeacherDto teacherDto, Long id);

    ResponseEntity<String> deleteTeacher(Long id);
}
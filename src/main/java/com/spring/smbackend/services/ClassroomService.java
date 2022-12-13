package com.spring.smbackend.services;

import com.spring.smbackend.entities.Classroom;
import com.spring.smbackend.models.ClassroomDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClassroomService {

    ResponseEntity<Classroom> createClassroom(ClassroomDto classroomDto);

    ResponseEntity<List<Classroom>> findAll();

    ResponseEntity<Classroom> findClassroomById(Long id);

    ResponseEntity<Classroom> updateClassroom(ClassroomDto classroomDto, Long id);

    ResponseEntity<String> deleteClassroom(Long id);
}
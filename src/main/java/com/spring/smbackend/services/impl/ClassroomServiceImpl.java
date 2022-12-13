package com.spring.smbackend.services.impl;

import com.spring.smbackend.entities.Classroom;
import com.spring.smbackend.exceptions.ResourceNotFoundException;
import com.spring.smbackend.models.ClassroomDto;
import com.spring.smbackend.repositories.ClassroomRepository;
import com.spring.smbackend.services.ClassroomService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;

    public ClassroomServiceImpl(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    @Override
    public ResponseEntity<Classroom> createClassroom(ClassroomDto classroomDto) {
        return ResponseEntity.status(200).build();
        //todo to complete
    }

    @Override
    public ResponseEntity<List<Classroom>> findAll() {
        List<Classroom> classroomList = this.classroomRepository.findAll();
        return ResponseEntity.status(200).body(classroomList);
    }

    @Override
    public ResponseEntity<Classroom> findClassroomById(Long id) {
        Classroom classroom = this.classroomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Classroom does not exist with : " + id));
        return ResponseEntity.status(200).body(classroom);
    }

    @Override
    public ResponseEntity<Classroom> updateClassroom(ClassroomDto classroomDto, Long id) {
        return ResponseEntity.status(200).build();
        //todo to complete
    }

    @Override
    public ResponseEntity<String> deleteClassroom(Long id) {
        this.classroomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Classroom does not exist with : " + id));
        this.classroomRepository.deleteById(id);
        return ResponseEntity.status(200).body("Classroom deleted successfully");
    }
}
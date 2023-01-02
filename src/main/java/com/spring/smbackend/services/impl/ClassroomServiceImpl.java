package com.spring.smbackend.services.impl;

import com.spring.smbackend.entities.Classroom;
import com.spring.smbackend.entities.Section;
import com.spring.smbackend.exceptions.ResourceNotFoundException;
import com.spring.smbackend.models.ClassroomDto;
import com.spring.smbackend.repositories.ClassroomRepository;
import com.spring.smbackend.repositories.SectionRepository;
import com.spring.smbackend.services.ClassroomService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final SectionRepository sectionRepository;

    public ClassroomServiceImpl(ClassroomRepository classroomRepository, SectionRepository sectionRepository) {
        this.classroomRepository = classroomRepository;
        this.sectionRepository = sectionRepository;
    }

    @Override
    public ResponseEntity<Classroom> createClassroom(ClassroomDto classroomDto) {
        Section section = this.sectionRepository.findById(classroomDto.getSectionId())
                .orElseThrow(() -> new ResourceNotFoundException("Section does not exist with id: " + classroomDto.getSectionId()));
        List<Classroom> classroomList = this.classroomRepository.findClassroomByNameAndSection(classroomDto.getName(), section);
        if (!classroomList.isEmpty()) return ResponseEntity.badRequest().build();
        Classroom classroom = new Classroom();
        classroom.setName(classroomDto.getName());
        classroom.setSection(section);
        this.classroomRepository.save(classroom);
        return ResponseEntity.status(200).body(classroom);

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
        Classroom newClassroom = this.classroomRepository.findById(classroomDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Classroom does not exist with id: " + id));
        Section section = this.sectionRepository.findById(classroomDto.getSectionId())
                .orElseThrow(() -> new ResourceNotFoundException("School does not exist with id: " + id));
        List<Classroom> classroomList = this.classroomRepository.findClassroomByNameAndSection(classroomDto.getName(), section);
        if (!classroomList.isEmpty()) {
            Stream<Classroom> classroomListFinal = classroomList.stream().filter(classroom -> !Objects.equals(classroom.getId(), classroomDto.getId()));
            if (!classroomListFinal.toList().isEmpty()) return ResponseEntity.badRequest().build();
        }
        newClassroom.setId(classroomDto.getId());
        newClassroom.setName(classroomDto.getName());
        newClassroom.setSection(section);
        this.classroomRepository.save(newClassroom);
        return ResponseEntity.status(200).body(newClassroom);
    }

    @Override
    public ResponseEntity<String> deleteClassroom(Long id) {
        this.classroomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Classroom does not exist with : " + id));
        this.classroomRepository.deleteById(id);
        return ResponseEntity.status(200).body("Classroom deleted successfully");
    }
}

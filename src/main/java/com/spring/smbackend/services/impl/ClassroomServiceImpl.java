package com.spring.smbackend.services.impl;

import com.spring.smbackend.entities.Classroom;
import com.spring.smbackend.repositories.ClassroomRepository;
import com.spring.smbackend.services.ClassroomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;

    public ClassroomServiceImpl(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    @Override
    public void createClassroom(Classroom classroom) {
        this.classroomRepository.save(classroom);
    }

    @Override
    public List<Classroom> findAll() {
        return this.classroomRepository.findAll();
    }

    @Override
    public Classroom findClassroomById(Long id) {
        return this.classroomRepository.findById(id).isPresent() ? this.classroomRepository.findById(id).get() : null;
    }

    @Override
    public List<Classroom> findClassroomByName(String name) {
        return this.classroomRepository.findClassroomByName(name);
    }

    @Override
    public void updateClassroom(Classroom classroom) {
        this.classroomRepository.save(classroom);
    }

    @Override
    public void deleteClassroom(Long id) {
        this.classroomRepository.deleteById(id);
    }
}
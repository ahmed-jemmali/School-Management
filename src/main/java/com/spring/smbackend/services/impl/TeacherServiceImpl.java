package com.spring.smbackend.services.impl;

import com.spring.smbackend.entities.Teacher;
import com.spring.smbackend.repositories.TeacherRepository;
import com.spring.smbackend.services.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    private TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public void createTeacher(Teacher teacher) {
        this.teacherRepository.save(teacher);
    }

    @Override
    public List<Teacher> findAll() {
        return this.teacherRepository.findAll();
    }

    @Override
    public Teacher findTeacherById(Long id) {
        return this.teacherRepository.findById(id).isPresent() ? this.teacherRepository.findById(id).get() : null;
    }

    @Override
    public List<Teacher> findTeacherByName(String name) {
        return this.teacherRepository.findTeacherByName(name);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        this.teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacher(Long id) {
        this.teacherRepository.deleteById(id);
    }
}
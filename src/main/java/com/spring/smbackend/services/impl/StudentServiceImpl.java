package com.spring.smbackend.services.impl;

import com.spring.smbackend.entities.Student;
import com.spring.smbackend.repositories.StudentRepository;
import com.spring.smbackend.services.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void createStudent(Student student) {
        this.studentRepository.save(student);
    }

    @Override
    public List<Student> findAll() {
        return this.studentRepository.findAll();
    }

    @Override
    public Student findStudentById(Long id) {
        return this.studentRepository.findById(id).isPresent() ? this.studentRepository.findById(id).get() : null;
    }

    @Override
    public List<Student> findStudentByName(String name) {
        return this.studentRepository.findStudentByName(name);
    }

    @Override
    public void updateStudent(Student student) {
        this.studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        this.studentRepository.deleteById(id);
    }
}
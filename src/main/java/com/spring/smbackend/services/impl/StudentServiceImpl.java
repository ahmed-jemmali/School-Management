package com.spring.smbackend.services.impl;

import com.spring.smbackend.entities.Student;
import com.spring.smbackend.exceptions.ResourceNotFoundException;
import com.spring.smbackend.repositories.StudentRepository;
import com.spring.smbackend.services.StudentService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Student>> findAll() {
        List<Student> studentList = this.studentRepository.findAll();
        return ResponseEntity.status(200).body(studentList);
    }

    @Override
    public ResponseEntity<Student> findStudentById(Long id) {
        Student student = this.studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student does not exist with id: " + id));
        return ResponseEntity.status(200).body(student);
    }

    @Override
    public void updateStudent(Student student) {
        this.studentRepository.save(student);
    }

    @Override
    public ResponseEntity<String> deleteStudent(Long id) {
        this.studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student does not exist with id: " + id));
        this.studentRepository.deleteById(id);
        return ResponseEntity.status(200).body("Student deleted successfully");
    }
}

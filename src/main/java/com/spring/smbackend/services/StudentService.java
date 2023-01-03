package com.spring.smbackend.services;

import com.spring.smbackend.entities.Student;
import com.spring.smbackend.models.StudentDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {

    ResponseEntity<Student> createStudent(StudentDto studentDto);

    ResponseEntity<List<Student>> findAll();

    ResponseEntity<Student> findStudentById(Long id);

    ResponseEntity<String> deleteStudent(Long id);
}

package com.spring.smbackend.services;

import com.spring.smbackend.entities.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {

    void createStudent(Student student);

    ResponseEntity<List<Student>> findAll();

    ResponseEntity<Student> findStudentById(Long id);


    void updateStudent(Student student);

    ResponseEntity<String> deleteStudent(Long id);
}

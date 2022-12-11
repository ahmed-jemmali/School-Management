package com.spring.smbackend.services;

import com.spring.smbackend.entities.Student;

import java.util.List;

public interface StudentService {

    void createStudent(Student student);

    List<Student> findAll();

    Student findStudentById(Long id);

    List<Student> findStudentByName(String name);

    void updateStudent(Student student);

    void deleteStudent(Long id);
}
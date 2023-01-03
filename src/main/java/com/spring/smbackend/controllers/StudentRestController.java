package com.spring.smbackend.controllers;

import com.spring.smbackend.entities.Student;
import com.spring.smbackend.models.StudentDto;
import com.spring.smbackend.services.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private final StudentService studentService;

    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/students")
    public ResponseEntity<Student> createStudent(@RequestBody StudentDto studentDto) {
        return this.studentService.createStudent(studentDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        return this.studentService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return this.studentService.findStudentById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/students/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody StudentDto studentDto, @PathVariable Long id) {
        return this.studentService.updateStudent(studentDto, id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/students/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        return this.studentService.deleteStudent(id);
    }
}

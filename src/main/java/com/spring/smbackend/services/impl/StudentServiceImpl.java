package com.spring.smbackend.services.impl;

import com.spring.smbackend.entities.Classroom;
import com.spring.smbackend.entities.Student;
import com.spring.smbackend.exceptions.ResourceNotFoundException;
import com.spring.smbackend.models.StudentDto;
import com.spring.smbackend.repositories.ClassroomRepository;
import com.spring.smbackend.repositories.StudentRepository;
import com.spring.smbackend.services.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ClassroomRepository classroomRepository;

    private StudentServiceImpl(StudentRepository studentRepository, ClassroomRepository classroomRepository) {
        this.studentRepository = studentRepository;
        this.classroomRepository = classroomRepository;
    }

    @Override
    public ResponseEntity<Student> createStudent(StudentDto studentDto) {
        Classroom classroom = this.classroomRepository.findById(studentDto.getClassroomId())
                .orElseThrow(() -> new ResourceNotFoundException("Classroom does not exists with id: " + studentDto.getClassroomId()));
        List<Student> studentList = this.studentRepository.findStudentsByNameAndEmail(studentDto.getName(), studentDto.getEmail());
        if (!studentList.isEmpty()) return ResponseEntity.badRequest().body(studentList.get(0));
        Student student = new Student();
        student.setName(studentDto.getName());
        student.setAge(studentDto.getAge());
        student.setParentName(studentDto.getParentName());
        student.setPhoneNumber(studentDto.getPhoneNumber());
        student.setEmail(studentDto.getEmail());
        student.setAddress(studentDto.getAddress());
        student.setClassroom(classroom);
        this.studentRepository.save(student);
        return ResponseEntity.status(200).body(student);
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
    public ResponseEntity<String> deleteStudent(Long id) {
        this.studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student does not exist with id: " + id));
        this.studentRepository.deleteById(id);
        return ResponseEntity.status(200).body("Student deleted successfully");
    }
}

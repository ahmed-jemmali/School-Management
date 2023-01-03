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
import java.util.Objects;
import java.util.stream.Stream;

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
        return getStudentResponseEntity(studentDto, student, classroom);
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
    public ResponseEntity<Student> updateStudent(StudentDto studentDto, Long id) {
        Student newStudent = this.studentRepository.findById(studentDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(""));
        Classroom classroom = this.classroomRepository.findById(studentDto.getClassroomId())
                .orElseThrow(() -> new ResourceNotFoundException(""));
        List<Student> studentList = this.studentRepository.findStudentsByNameAndEmail(studentDto.getName(), studentDto.getEmail());
        if (studentList != null && studentList.size() > 0) {
            Stream<Student> studentListFinal = studentList.stream().filter((student -> !Objects.equals(student.getId(), studentDto.getId())));
            if (!studentListFinal.toList().isEmpty()) return ResponseEntity.badRequest().build();
        }
        newStudent.setId(studentDto.getId());
        return getStudentResponseEntity(studentDto, newStudent, classroom);
    }

    private ResponseEntity<Student> getStudentResponseEntity(StudentDto studentDto, Student newStudent, Classroom classroom) {
        newStudent.setName(studentDto.getName());
        newStudent.setAge(studentDto.getAge());
        newStudent.setParentName(studentDto.getParentName());
        newStudent.setPhoneNumber(studentDto.getPhoneNumber());
        newStudent.setEmail(studentDto.getEmail());
        newStudent.setAddress(studentDto.getAddress());
        newStudent.setClassroom(classroom);
        this.studentRepository.save(newStudent);
        return ResponseEntity.status(200).body(newStudent);
    }

    @Override
    public ResponseEntity<String> deleteStudent(Long id) {
        this.studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student does not exist with id: " + id));
        this.studentRepository.deleteById(id);
        return ResponseEntity.status(200).body("Student deleted successfully");
    }
}

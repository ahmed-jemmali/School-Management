package com.spring.smbackend.services.impl;

import com.spring.smbackend.entities.Classroom;
import com.spring.smbackend.entities.Teacher;
import com.spring.smbackend.exceptions.ResourceNotFoundException;
import com.spring.smbackend.models.TeacherDto;
import com.spring.smbackend.repositories.ClassroomRepository;
import com.spring.smbackend.repositories.TeacherRepository;
import com.spring.smbackend.services.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final ClassroomRepository classroomRepository;

    private TeacherServiceImpl(TeacherRepository teacherRepository, ClassroomRepository classroomRepository) {
        this.teacherRepository = teacherRepository;
        this.classroomRepository = classroomRepository;
    }

    @Override
    public ResponseEntity<Teacher> createTeacher(TeacherDto teacherDto) {
        List<Teacher> teacherList = this.teacherRepository.findTeachersByNameAndEmail(teacherDto.getName(), teacherDto.getEmail());
        if (!teacherList.isEmpty()) return ResponseEntity.badRequest().build();
        Teacher teacher = new Teacher();
        return getTeacherResponseEntity(teacherDto, teacher);
    }

    @Override
    public ResponseEntity<List<Teacher>> findAll() {
        List<Teacher> teacherList = this.teacherRepository.findAll();
        return ResponseEntity.status(200).body(teacherList);
    }

    @Override
    public ResponseEntity<Teacher> findTeacherById(Long id) {
        Teacher teacher = this.teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher does not exist with id: " + id));
        return ResponseEntity.status(200).body(teacher);
    }

    @Override
    public ResponseEntity<Teacher> updateTeacher(TeacherDto teacherDto, Long id) {
        Teacher newTeacher = this.teacherRepository.findById(teacherDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(""));
        List<Teacher> teacherList = this.teacherRepository.findTeachersByNameAndEmail(teacherDto.getName(), teacherDto.getEmail());
        if (teacherList.size() > 0) {
            Stream<Teacher> teacherListFinal = teacherList.stream().filter(teacher -> !Objects.equals(teacher.getId(), teacherDto.getId()));
            if (!teacherListFinal.toList().isEmpty()) return ResponseEntity.badRequest().build();
        }
        newTeacher.setId(teacherDto.getId());
        return getTeacherResponseEntity(teacherDto, newTeacher);

    }

    @Override
    public ResponseEntity<String> deleteTeacher(Long id) {
        this.teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher does not exist with id: " + id));
        this.teacherRepository.deleteById(id);
        return ResponseEntity.status(200).body("Teacher deleted successfully");
    }

    @Override
    public ResponseEntity<Teacher> assignClassroomToTeacher(Long teacherId, Long classroomId) {
        Set<Classroom> classroomSet = null;
        Teacher teacher = this.teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher does not exist with id: " + teacherId));
        Classroom classroom = this.classroomRepository.findById(classroomId)
                .orElseThrow(() -> new ResourceNotFoundException("Classroom does not exist with id: " + classroomId));
        classroomSet = teacher.getClassrooms();
        if (classroomSet.contains(classroom))
            throw new RuntimeException("This classroom is already assigned for this teacher");
        classroomSet.add(classroom);
        teacher.setClassrooms(classroomSet);
        this.teacherRepository.save(teacher);
        return ResponseEntity.status(200).body(teacher);
    }

    private ResponseEntity<Teacher> getTeacherResponseEntity(TeacherDto teacherDto, Teacher teacher) {
        teacher.setName(teacherDto.getName());
        teacher.setPhoneNumber(teacherDto.getPhoneNumber());
        teacher.setEmail(teacherDto.getEmail());
        teacher.setAddress(teacherDto.getAddress());
        teacher.setDescription(teacherDto.getDescription());
        this.teacherRepository.save(teacher);
        return ResponseEntity.status(200).body(teacher);
    }
}

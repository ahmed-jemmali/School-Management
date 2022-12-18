package com.spring.smbackend.controllers;

import com.spring.smbackend.entities.Teacher;
import com.spring.smbackend.models.TeacherDto;
import com.spring.smbackend.services.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TeacherRestController {

    private final TeacherService teacherService;

    public TeacherRestController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/teachers")
    public ResponseEntity<Teacher> createTeacher(@RequestBody TeacherDto teacherDto) {
        return this.teacherService.createTeacher(teacherDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/teachers")
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        return this.teacherService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/teachers/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) {
        return this.teacherService.findTeacherById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/teachers/{id}")
    public ResponseEntity<Teacher> updateTeacher(@RequestBody TeacherDto teacherDto, @PathVariable Long id) {
        return this.teacherService.updateTeacher(teacherDto, id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/teachers/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable Long id) {
        return this.teacherService.deleteTeacher(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{teacherId}/teachers/{classroomId}")
    public Teacher assignClassroomToTeacher(@PathVariable Long teacherId, @PathVariable Long classroomId) {
        return teacherService.assignClassroomToTeacher(teacherId, classroomId);
    }
}
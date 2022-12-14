package com.spring.smbackend.controllers;

import com.spring.smbackend.entities.Classroom;
import com.spring.smbackend.models.ClassroomDto;
import com.spring.smbackend.services.ClassroomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClassroomRestController {

    private final ClassroomService classroomService;

    public ClassroomRestController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "classrooms")
    public ResponseEntity<Classroom> createClassroom(@RequestBody ClassroomDto classroomDto) {
        return this.classroomService.createClassroom(classroomDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "classrooms")
    public ResponseEntity<List<Classroom>> getAllClassroom() {
        return this.classroomService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "classrooms/{id}")
    public ResponseEntity<Classroom> getClassroomById(@PathVariable Long id) {
        return this.classroomService.findClassroomById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "classrooms{id}")
    public ResponseEntity<Classroom> updateClassroom(@RequestBody ClassroomDto classroomDto, Long id) {
        return this.classroomService.updateClassroom(classroomDto, id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "classrooms/{id}")
    public ResponseEntity<String> deleteClassroom(@PathVariable Long id) {
        return this.classroomService.deleteClassroom(id);
    }
}
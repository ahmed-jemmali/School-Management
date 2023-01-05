package com.spring.smbackend.controllers;

import com.spring.smbackend.entities.School;
import com.spring.smbackend.models.SchoolDto;
import com.spring.smbackend.services.SchoolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SchoolRestController {
    private final SchoolService schoolService;

    public SchoolRestController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/schools")
    public ResponseEntity<School> createSchool(@RequestBody SchoolDto schoolDto) {
        return this.schoolService.createSchool(schoolDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/schools")
    public ResponseEntity<List<School>> getAllSchools() {
        return this.schoolService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/schools/{id}")
    public ResponseEntity<School> getSchoolById(@PathVariable Long id) {
        return this.schoolService.findSchoolById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/schools/{id}")
    public ResponseEntity<School> updateSchool(@RequestBody SchoolDto schoolDto, @PathVariable Long id) {
        return this.schoolService.updateSchool(schoolDto, id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/schools/{id}")
    public ResponseEntity<String> deleteSchool(@PathVariable Long id) {
        return this.schoolService.deleteSchool(id);
    }
}

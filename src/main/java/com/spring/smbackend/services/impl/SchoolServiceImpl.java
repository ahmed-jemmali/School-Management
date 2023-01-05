package com.spring.smbackend.services.impl;

import com.spring.smbackend.entities.School;
import com.spring.smbackend.exceptions.ResourceNotFoundException;
import com.spring.smbackend.models.SchoolDto;
import com.spring.smbackend.repositories.SchoolRepository;
import com.spring.smbackend.services.SchoolService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;

    private SchoolServiceImpl(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    public ResponseEntity<School> createSchool(SchoolDto schoolDto) {
        School school = new School();
        school.setName(schoolDto.getName());
        school.setAddress(schoolDto.getAddress());
        school.setDescription(schoolDto.getDescription());
        this.schoolRepository.save(school);
        return ResponseEntity.status(200).body(school);
    }

    @Override
    public ResponseEntity<List<School>> findAll() {
        List<School> schoolList = this.schoolRepository.findAll();
        return ResponseEntity.status(200).body(schoolList);
    }

    @Override
    public ResponseEntity<School> findSchoolById(Long id) {
        School school = this.schoolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("School does not exist with id: " + id));
        return ResponseEntity.status(200).body(school);
    }

    @Override
    public ResponseEntity<String> deleteSchool(Long id) {
        this.schoolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("School does not exist with id: " + id));
        this.schoolRepository.deleteById(id);
        return ResponseEntity.status(200).body("School deleted successfully");

    }
}

package com.spring.smbackend.services.impl;

import com.spring.smbackend.entities.School;
import com.spring.smbackend.repositories.SchoolRepository;
import com.spring.smbackend.services.SchoolService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;

    private SchoolServiceImpl(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    public void createSchool(School school) {
        this.schoolRepository.save(school);
    }

    @Override
    public List<School> findAll() {
        return this.schoolRepository.findAll();
    }

    @Override
    public School findSchoolById(Long id) {
        return this.schoolRepository.findById(id).isPresent() ? this.schoolRepository.findById(id).get() : null;
    }

    @Override
    public List<School> findSchoolByName(String name) {
        return this.schoolRepository.findSchoolByName(name);
    }

    @Override
    public void updateSchool(School school) {
        this.schoolRepository.save(school);
    }

    @Override
    public void deleteSchool(Long id) {
        this.schoolRepository.deleteById(id);
    }
}
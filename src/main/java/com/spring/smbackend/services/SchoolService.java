package com.spring.smbackend.services;

import com.spring.smbackend.entities.School;

import java.util.List;

public interface SchoolService {

    void createSchool(School school);

    List<School> findAll();

    School findSchoolById(Long id);

    List<School> findSchoolByName(String name);

    void updateSchool(School school);

    void deleteSchool(Long id);
}
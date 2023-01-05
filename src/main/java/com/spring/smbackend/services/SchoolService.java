package com.spring.smbackend.services;

import com.spring.smbackend.entities.School;
import com.spring.smbackend.models.SchoolDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SchoolService {

    ResponseEntity<List<School>> findAll();

    ResponseEntity<School> findSchoolById(Long id);

    ResponseEntity<String> deleteSchool(Long id);
}

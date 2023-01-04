package com.spring.smbackend.services;

import com.spring.smbackend.entities.Section;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SectionService {

    ResponseEntity<List<Section>> findAll();

    ResponseEntity<Section> findSectionById(Long id);

    ResponseEntity<String> deleteSection(Long id);
}

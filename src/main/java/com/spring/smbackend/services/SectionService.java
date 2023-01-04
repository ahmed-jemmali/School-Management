package com.spring.smbackend.services;

import com.spring.smbackend.entities.Section;
import com.spring.smbackend.models.SectionDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SectionService {

    ResponseEntity<Section> createSection(SectionDto sectionDto);

    ResponseEntity<List<Section>> findAll();

    ResponseEntity<Section> findSectionById(Long id);

    ResponseEntity<Section> updateSection(SectionDto sectionDto, Long id);

    ResponseEntity<String> deleteSection(Long id);
}

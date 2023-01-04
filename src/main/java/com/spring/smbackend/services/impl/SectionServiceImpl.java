package com.spring.smbackend.services.impl;

import com.spring.smbackend.entities.School;
import com.spring.smbackend.entities.Section;
import com.spring.smbackend.exceptions.ResourceNotFoundException;
import com.spring.smbackend.models.SectionDto;
import com.spring.smbackend.repositories.SchoolRepository;
import com.spring.smbackend.repositories.SectionRepository;
import com.spring.smbackend.services.SectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;
    private final SchoolRepository schoolRepository;

    public SectionServiceImpl(SectionRepository sectionRepository, SchoolRepository schoolRepository) {
        this.sectionRepository = sectionRepository;
        this.schoolRepository = schoolRepository;
    }

    @Override
    public ResponseEntity<Section> createSection(SectionDto sectionDto) {
        School school = this.schoolRepository.findById(sectionDto.getSchoolId())
                .orElseThrow(() -> new ResourceNotFoundException(""));
        List<Section> sectionList = this.sectionRepository.findSectionsByName(sectionDto.getName());
        if (sectionList != null && sectionList.size() > 0) return ResponseEntity.badRequest().build();
        Section section = new Section();
        section.setName(section.getName());
        section.setSchool(school);
        this.sectionRepository.save(section);
        return ResponseEntity.status(200).body(section);
    }

    @Override
    public ResponseEntity<List<Section>> findAll() {
        List<Section> sectionList = this.sectionRepository.findAll();
        return ResponseEntity.status(200).body(sectionList);
    }

    @Override
    public ResponseEntity<Section> findSectionById(Long id) {
        Section section = this.sectionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Section does not exist with id: " + id));
        return ResponseEntity.status(200).body(section);
    }

    @Override
    public ResponseEntity<String> deleteSection(Long id) {
        this.sectionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Section does not exist with id: " + id));
        this.sectionRepository.deleteById(id);
        return ResponseEntity.status(200).body("Section deleted successfully");
    }
}

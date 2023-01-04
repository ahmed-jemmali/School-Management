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
import java.util.Objects;
import java.util.stream.Stream;

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
                .orElseThrow(() -> new ResourceNotFoundException("School does not exist with id: " + sectionDto.getSchoolId()));
        List<Section> sectionList = this.sectionRepository.findSectionsByName(sectionDto.getName());
        if (sectionList != null && sectionList.size() > 0) return ResponseEntity.badRequest().build();
        Section section = new Section();
        return this.getSectionResponseEntity(sectionDto, section, school);
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
    public ResponseEntity<Section> updateSection(SectionDto sectionDto, Long id) {
        Section newSection = this.sectionRepository.findById(sectionDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Section does not exist with id: " + id));
        School school = this.schoolRepository.findById(sectionDto.getSchoolId())
                .orElseThrow(() -> new ResourceNotFoundException("School does not exist with id: " + sectionDto.getSchoolId()));
        List<Section> sectionList = this.sectionRepository.findSectionsByName(sectionDto.getName());
        if (sectionList != null && !sectionList.isEmpty()) {
            Stream<Section> sectionStream = sectionList.stream().filter(section -> !Objects.equals(section.getId(), sectionDto.getId()));
            if (!sectionStream.toList().isEmpty()) return ResponseEntity.badRequest().build();
        }
        newSection.setId(sectionDto.getId());
        return this.getSectionResponseEntity(sectionDto, newSection, school);
    }

    private ResponseEntity<Section> getSectionResponseEntity(SectionDto sectionDto, Section newSection, School school) {
        newSection.setName(sectionDto.getName());
        newSection.setSchool(school);
        this.sectionRepository.save(newSection);
        return ResponseEntity.status(200).body(newSection);
    }

    @Override
    public ResponseEntity<String> deleteSection(Long id) {
        this.sectionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Section does not exist with id: " + id));
        this.sectionRepository.deleteById(id);
        return ResponseEntity.status(200).body("Section deleted successfully");
    }
}

package com.spring.smbackend.services.impl;

import com.spring.smbackend.entities.Section;
import com.spring.smbackend.exceptions.ResourceNotFoundException;
import com.spring.smbackend.repositories.SectionRepository;
import com.spring.smbackend.services.SectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;

    public SectionServiceImpl(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
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

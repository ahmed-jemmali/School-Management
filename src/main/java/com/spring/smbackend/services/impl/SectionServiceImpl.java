package com.spring.smbackend.services.impl;

import com.spring.smbackend.entities.Section;
import com.spring.smbackend.repositories.SectionRepository;
import com.spring.smbackend.services.SectionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;

    public SectionServiceImpl(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    @Override
    public void createSection(Section section) {
        this.sectionRepository.save(section);
    }

    @Override
    public List<Section> findAll() {
        return this.sectionRepository.findAll();
    }

    @Override
    public Section findSectionById(Long id) {
        return this.sectionRepository.findById(id).isPresent() ? this.sectionRepository.findById(id).get() : null;
    }

    @Override
    public List<Section> findSectionByName(String name) {
        return this.sectionRepository.findSectionByName(name);
    }

    @Override
    public void updateSection(Section section) {
        this.sectionRepository.save(section);
    }

    @Override
    public void deleteSection(Long id) {
        this.sectionRepository.deleteById(id);
    }
}
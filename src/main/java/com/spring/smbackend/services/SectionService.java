package com.spring.smbackend.services;

import com.spring.smbackend.entities.Section;

import java.util.List;

public interface SectionService {

    void createSection(Section section);

    List<Section> findAll();

    Section findSectionById(Long id);

    List<Section> findSectionByName(String name);

    void updateSection(Section section);

    void deleteSection(Long id);
}
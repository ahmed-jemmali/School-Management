package com.spring.smbackend.controllers;

import com.spring.smbackend.entities.Section;
import com.spring.smbackend.models.SectionDto;
import com.spring.smbackend.services.SectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SectionRestController {
    private final SectionService sectionService;

    public SectionRestController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/sections")
    public ResponseEntity<Section> createSection(@RequestBody SectionDto sectionDto) {
        return this.sectionService.createSection(sectionDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sections")
    public ResponseEntity<List<Section>> getAllSections() {
        return this.sectionService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sections/{id}")
    public ResponseEntity<Section> getSectionById(@PathVariable Long id) {
        return this.sectionService.findSectionById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/sections/{id}")
    public ResponseEntity<Section> updateSection(@RequestBody SectionDto sectionDto, @PathVariable Long id) {
        return this.sectionService.updateSection(sectionDto, id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/sections/{id}")
    public ResponseEntity<String> deleteSection(@PathVariable Long id) {
        return this.sectionService.deleteSection(id);
    }
}

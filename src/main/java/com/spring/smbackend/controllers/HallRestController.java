package com.spring.smbackend.controllers;

import com.spring.smbackend.entities.Hall;
import com.spring.smbackend.models.HallDto;
import com.spring.smbackend.services.HallService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HallRestController {

    private final HallService hallService;//So we can only assign a value to it once

    public HallRestController(HallService hallService) {
        this.hallService = hallService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/halls")
    public ResponseEntity<Hall> createHall(@RequestBody HallDto hallDto) {
        return this.hallService.createHall(hallDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/halls")
    public ResponseEntity<List<Hall>> getAllHalls() {
        return this.hallService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/halls/{id}")
    public ResponseEntity<Hall> getHallById(@PathVariable Long id) {
        return this.hallService.findHallById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/halls/{id}")
    public ResponseEntity<Hall> updateHall(@RequestBody HallDto hallDto, @PathVariable Long id) {
        return this.hallService.updateHall(hallDto, id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "halls/{id}")
    public ResponseEntity<String> deleteHall(@PathVariable Long id) {
        return this.hallService.deleteHall(id);
    }
}
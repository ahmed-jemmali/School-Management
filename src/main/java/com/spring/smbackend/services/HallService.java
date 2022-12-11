package com.spring.smbackend.services;

import com.spring.smbackend.entities.Hall;
import com.spring.smbackend.models.HallDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HallService {

    ResponseEntity<Hall> createHall(HallDto hallDto);

    ResponseEntity<List<Hall>> findAll();

    ResponseEntity<Hall> findHallById(Long id);

    ResponseEntity<Hall> updateHall(HallDto hallDto, Long id);

    ResponseEntity<String> deleteHall(Long id);
}

package com.spring.smbackend.services.impl;

import com.spring.smbackend.entities.Department;
import com.spring.smbackend.entities.Hall;
import com.spring.smbackend.exceptions.ResourceNotFoundException;
import com.spring.smbackend.models.HallDto;
import com.spring.smbackend.repositories.DepartmentRepository;
import com.spring.smbackend.repositories.HallRepository;
import com.spring.smbackend.services.HallService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class HallServiceImpl implements HallService {

    private final HallRepository hallRepository;
    private final DepartmentRepository departmentRepository;

    private HallServiceImpl(HallRepository hallRepository, DepartmentRepository departmentRepository) {
        this.hallRepository = hallRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public ResponseEntity<Hall> createHall(HallDto hallDto) {
        Department department = this.departmentRepository.findById(hallDto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department does not exist with id: " + hallDto.getDepartmentId()));
        List<Hall> hallList = this.hallRepository.findHallsByNameAndDepartment(hallDto.getName(), department);
        if (!hallList.isEmpty()) return ResponseEntity.badRequest().build();
        Hall newHall = new Hall();
        newHall.setId(hallDto.getId());
        newHall.setName(hallDto.getName());
        newHall.setFloor(hallDto.getFloor());
        newHall.setDepartment(department);
        this.hallRepository.save(newHall);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Hall>> findAll() {
        List<Hall> hallList = this.hallRepository.findAll();
        return ResponseEntity.status(200).body(hallList);
    }

    @Override
    public ResponseEntity<Hall> findHallById(Long id) {
        Hall hall = this.hallRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hall does not exist with id: " + id));
        return ResponseEntity.ok(hall);
    }

    // todo return updated HallDto
    @Override
    public ResponseEntity<Hall> updateHall(HallDto hallDto, Long id) {
        Hall newHall = this.hallRepository.findById(hallDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Hall does not exist with id: " + id));
        Department department = this.departmentRepository.findById(hallDto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department does not exist with id: " + id));
        List<Hall> hallList = this.hallRepository.findHallsByNameAndDepartment(hallDto.getName(), department);
        if (hallList.size() > 0) {
            Stream<Hall> hallListFinal = hallList.stream().filter(hall -> !Objects.equals(hall.getId(), hallDto.getId()));
            if (!hallListFinal.toList().isEmpty()) return ResponseEntity.badRequest().build();
        }
        newHall.setName(hallDto.getName());
        newHall.setFloor(hallDto.getFloor());
        newHall.setDepartment(department);
        this.hallRepository.save(newHall);
        return ResponseEntity.status(200).body(newHall);
    }

    @Override
    public ResponseEntity<String> deleteHall(Long id) {
        this.hallRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hall does not exist with id: " + id));
        this.hallRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Hall deleted successfully");
    }
}
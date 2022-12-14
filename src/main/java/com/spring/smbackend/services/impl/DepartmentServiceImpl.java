package com.spring.smbackend.services.impl;

import com.spring.smbackend.entities.Department;
import com.spring.smbackend.entities.School;
import com.spring.smbackend.exceptions.ResourceNotFoundException;
import com.spring.smbackend.models.DepartmentDto;
import com.spring.smbackend.repositories.DepartmentRepository;
import com.spring.smbackend.repositories.SchoolRepository;
import com.spring.smbackend.services.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final SchoolRepository schoolRepository;

    private DepartmentServiceImpl(DepartmentRepository departmentRepository, SchoolRepository schoolRepository) {
        this.departmentRepository = departmentRepository;
        this.schoolRepository = schoolRepository;
    }

    @Override
    public ResponseEntity<Department> createDepartment(DepartmentDto departmentDto) {
        School school = this.schoolRepository.findById(departmentDto.getSchoolId())
                .orElseThrow(() -> new ResourceNotFoundException("School does not exists with id: " + departmentDto.getSchoolId()));
        List<Department> departmentList = this.departmentRepository.findDepartmentsByName(departmentDto.getName());
        if (!departmentList.isEmpty()) return ResponseEntity.badRequest().build();
        Department department = new Department();
        department.setName(departmentDto.getName());
        department.setSchool(school);
        this.departmentRepository.save(department);
        return ResponseEntity.status(200).body(department);
    }

    @Override
    public ResponseEntity<List<Department>> findAll() {
        List<Department> departmentList = this.departmentRepository.findAll();
        return ResponseEntity.status(200).body(departmentList);
    }

    @Override
    public ResponseEntity<Department> findDepartmentById(Long id) {
        Department department = this.departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department does not exist with id: " + id));
        return ResponseEntity.status(200).body(department);
    }

    @Override
    public ResponseEntity<Department> updateDepartment(DepartmentDto departmentDto, Long id) {
        Department newDepartment = this.departmentRepository.findById(departmentDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Department does not exist with id: " + id));
        School school = this.schoolRepository.findById(departmentDto.getSchoolId())
                .orElseThrow(() -> new ResourceNotFoundException("School does not exist with id: " + departmentDto.getSchoolId()));
        List<Department> departmentList = this.departmentRepository.findDepartmentsByName(departmentDto.getName());
        if (departmentList.size() > 0) {
            Stream<Department> departmentListFinal = departmentList.stream().filter(department -> !Objects.equals(department.getId(), departmentDto.getId()));
            if (!departmentListFinal.toList().isEmpty()) return ResponseEntity.badRequest().build();
        }
        newDepartment.setId(departmentDto.getId());
        newDepartment.setName(departmentDto.getName());
        newDepartment.setSchool(school);
        this.departmentRepository.save(newDepartment);
        return ResponseEntity.status(200).body(newDepartment);
    }

    @Override
    public ResponseEntity<String> deleteDepartment(Long id) {
        this.departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department does not exist with : " + id));
        this.departmentRepository.deleteById(id);
        return ResponseEntity.status(200).body("Department deleted successfully");
    }
}
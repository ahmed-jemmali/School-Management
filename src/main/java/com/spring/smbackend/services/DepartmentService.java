package com.spring.smbackend.services;

import com.spring.smbackend.entities.Department;
import com.spring.smbackend.models.DepartmentDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DepartmentService {

    ResponseEntity<Department> createDepartment(DepartmentDto departmentDto);

    ResponseEntity<List<Department>> findAll();

    ResponseEntity<Department> findDepartmentById(Long id);

    ResponseEntity<Department> updateDepartment(DepartmentDto departmentDto, Long id);

    ResponseEntity<String> deleteDepartment(Long id);
}
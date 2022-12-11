package com.spring.smbackend.controllers;

import com.spring.smbackend.entities.Department;
import com.spring.smbackend.models.DepartmentDto;
import com.spring.smbackend.services.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DepartmentRestController {

    private final DepartmentService departmentService;

    public DepartmentRestController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/departments")
    public ResponseEntity<Department> createDepartment(@RequestBody DepartmentDto departmentDto) {
        return this.departmentService.createDepartment(departmentDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/departments")
    public ResponseEntity<List<Department>> getAllDepartments() {
        return this.departmentService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/departments/{id}")
    public ResponseEntity<Department> getOneDepartment(@PathVariable Long id) {
        return this.departmentService.findDepartmentById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/departments/{id}")
    public ResponseEntity<Department> updateDepartment(@RequestBody DepartmentDto departmentDto, @PathVariable Long id) {
        return this.departmentService.updateDepartment(departmentDto, id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/departments/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
        return this.departmentService.deleteDepartment(id);
    }
}
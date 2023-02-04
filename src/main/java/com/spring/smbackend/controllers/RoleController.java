package com.spring.smbackend.controllers;

import com.spring.smbackend.entities.Role;
import com.spring.smbackend.services.RoleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping(value = "/roles")
    public void createRole(@RequestBody Role role) {
        roleService.createRole(role);
    }

    @GetMapping(value = "/roles")
    public void getAllRoles() {
        roleService.findAll();
    }
}

package com.spring.smbackend.services;

import com.spring.smbackend.entities.Role;

import java.util.List;

public interface RoleService {

    void createRole(Role role);

    List<Role> findAll();

    Role findByName(String name);

}

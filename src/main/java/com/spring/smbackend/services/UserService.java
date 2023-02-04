package com.spring.smbackend.services;

import com.spring.smbackend.entities.AppUser;

import java.util.List;

public interface UserService {

    void createUser(AppUser user);

    List<AppUser> findAll();
}

package com.spring.smbackend.controllers;

import com.spring.smbackend.entities.AppUser;
import com.spring.smbackend.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/users")
    public void createUser(@RequestBody AppUser user) {
        userService.createUser(user);
    }

    @GetMapping(value = "/users")
    public void getAllUsers() {
        userService.findAll();
    }

}

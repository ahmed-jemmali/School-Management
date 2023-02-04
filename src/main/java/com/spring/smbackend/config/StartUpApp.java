package com.spring.smbackend.config;

import com.spring.smbackend.entities.AppUser;
import com.spring.smbackend.entities.Role;
import com.spring.smbackend.services.RoleService;
import com.spring.smbackend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartUpApp implements CommandLineRunner {

    private final Log logger = LogFactory.getLog(StartUpApp.class);
    private final UserService userService;
    private final RoleService roleService;

    @Override
    public void run(String... args) throws Exception {

        if (roleService.findAll().isEmpty()) {
            logger.info("No roles found. Creating some roles");
            roleService.createRole(new Role(null, "admin"));
            roleService.createRole(new Role(null, "user"));
            roleService.createRole(new Role(null, "teacher"));
            roleService.createRole(new Role(null, "student"));
        }

        if (userService.findAll().isEmpty()) {
            logger.info("No users accounts found. Creating some users");
            Role role = roleService.findByName("admin");
            AppUser user = new AppUser("Ahmed Jm", "+216 27901748", "Ariana,Tunisia", "ahm.jemmali@gmail.com", "password", role);
            userService.createUser(user);
        }
    }
}

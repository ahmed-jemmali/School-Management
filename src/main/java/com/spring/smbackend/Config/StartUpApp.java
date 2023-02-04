package com.spring.smbackend.Config;

import com.spring.smbackend.entities.AppUser;
import com.spring.smbackend.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
@RequiredArgsConstructor
public class StartUpApp implements CommandLineRunner {

    private final Log logger = LogFactory.getLog(StartUpApp.class);

    private final UserServiceImpl userService;

    @Override
    public void run(String... args) throws Exception {
        if (userService.findAll().isEmpty()) {
            logger.info("No Users accounts found. Creating some users");
            AppUser user = new AppUser("Ahmed Jm", "27901748", "Ariana,Tunisia", "ahm.jemmali@gmail.com", "password");
            userService.createUser(user);
        }
    }
}

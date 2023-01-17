package com.spring.smbackend.Util;

import com.spring.smbackend.security.AppUser;
import com.spring.smbackend.security.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FirstTimeInitializer implements CommandLineRunner {

    private final Log logger = LogFactory.getLog(FirstTimeInitializer.class);

    private final UserService userService;

    public FirstTimeInitializer(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userService.findAll().isEmpty()) {
            logger.info("No Users accounts found. Creating some users");

            AppUser user = new AppUser("Ahmed", "ahm.jemmali@gmail.com", "password");
            userService.save(user);
        }
    }
}

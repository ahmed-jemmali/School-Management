package com.spring.smbackend.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final TokenUtil tokenUtil;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public AuthController(TokenUtil tokenUtil, UserService userService, AuthenticationManager authenticationManager) {
        this.tokenUtil = tokenUtil;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(value = {"", "/"})
    public JwtResponse signIn(@RequestBody SignInRequest signInRequest) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userService.loadUserByUsername(signInRequest.getUsername());
        String token = tokenUtil.generateToken(userDetails);
        return new JwtResponse(token);
    }
}

package com.spring.smbackend.services.impl;

import com.spring.smbackend.entities.AppUser;
import com.spring.smbackend.exceptions.ResourceNotFoundException;
import com.spring.smbackend.repositories.UserRepository;
import com.spring.smbackend.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*return new User("ahmed", passwordEncoder().encode("password"), AuthorityUtils.NO_AUTHORITIES);*/
        List<AppUser> user = userRepository.findByEmail(username);
        if (user.isEmpty()) throw new ResourceNotFoundException("User not found.");
        return user.get(0);
    }

    @Override
    public void createUser(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
    }

    @Override
    public List<AppUser> findAll() {
        return this.userRepository.findAll();
    }
}

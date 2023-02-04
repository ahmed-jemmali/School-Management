package com.spring.smbackend.repositories;

import com.spring.smbackend.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    List<AppUser> findByEmail(String email);
}

package com.spring.smbackend.repositories;

import com.spring.smbackend.entities.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findStudentByName(@Param("name") String name);
}
package com.spring.smbackend.repositories;

import com.spring.smbackend.entities.Student;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findStudentsByNameAndEmail(String name, String email);
}

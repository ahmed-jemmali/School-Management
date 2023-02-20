package com.spring.smbackend.repositories;

import com.spring.smbackend.entities.Teacher;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findTeachersByNameAndEmail(String name, String email);
}

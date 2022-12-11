package com.spring.smbackend.repositories;

import com.spring.smbackend.entities.Teacher;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findTeacherByName(@Param("name") String name);
}
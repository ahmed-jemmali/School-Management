package com.spring.smbackend.repositories;

import com.spring.smbackend.entities.Department;
import com.spring.smbackend.entities.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {
    List<Hall> findHallsByNameAndDepartment(String name, Department department);
}

package com.spring.smbackend.repositories;

import com.spring.smbackend.entities.School;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
    List<School> findSchoolByName(@Param("name") String name);
}

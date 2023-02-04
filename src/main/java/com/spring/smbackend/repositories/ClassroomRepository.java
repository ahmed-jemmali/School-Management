package com.spring.smbackend.repositories;

import com.spring.smbackend.entities.Classroom;
import com.spring.smbackend.entities.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    List<Classroom> findClassroomByNameAndSection(String name, Section section);
}

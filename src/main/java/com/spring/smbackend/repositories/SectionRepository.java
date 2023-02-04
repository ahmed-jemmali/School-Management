package com.spring.smbackend.repositories;

import com.spring.smbackend.entities.Section;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    List<Section> findSectionsByName(@Param("name") String name);
}

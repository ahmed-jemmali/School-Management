package com.spring.smbackend.services;

import com.spring.smbackend.entities.Classroom;

import java.util.List;

public interface ClassroomService {

    void createClassroom(Classroom classroom);

    List<Classroom> findAll();

    Classroom findClassroomById(Long id);

    List<Classroom> findClassroomByName(String name);

    void updateClassroom(Classroom classroom);

    void deleteClassroom(Long id);
}
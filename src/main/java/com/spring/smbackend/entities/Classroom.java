package com.spring.smbackend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    @JsonBackReference
    @OneToMany(mappedBy = "classroom")
    Collection<Student> students;
    @ManyToOne
    Section section;
    @ManyToMany
    Collection<Teacher> teachers;
}
//TODO Super() : class extents class
//TODO LOCAL VARIABLE ET GLOBAL VARIABLE: const, let and var in if(), function(), and for()
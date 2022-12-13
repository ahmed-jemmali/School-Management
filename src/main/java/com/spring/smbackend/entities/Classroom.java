package com.spring.smbackend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Validated
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @NotNull(message = "Name must not be null")
    private String name;
    @JsonBackReference
    @OneToMany(mappedBy = "classroom")
    private Collection<Student> students;
    @NotNull(message = "Section must not be null")
    @ManyToOne
    private Section section;
    @JsonIgnore
    @ManyToMany(mappedBy = "classrooms")
    private Set<Teacher> teachers;
}
//TODO Super() : class extents class
//TODO LOCAL VARIABLE ET GLOBAL VARIABLE: const, let and var in if(), function(), and for()
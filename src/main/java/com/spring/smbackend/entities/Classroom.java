package com.spring.smbackend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @OneToMany(mappedBy = "classroom")
    @JsonBackReference
    private Collection<Student> students;

    @NotNull(message = "Section must not be null")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    private Section section;

    @ManyToMany(mappedBy = "classrooms", fetch = FetchType.LAZY)
    private Set<Teacher> teachers;
}

//TODO Super() : class extents class
//TODO LOCAL VARIABLE ET GLOBAL VARIABLE: const, let and var in if(), function(), and for()
//todo @JoinColumn(name = "section_id", nullable = false, referencedColumnName = "id")
//todo @JsonIgnore // the property is invisible whether on create or get
//todo @Table(name = "classroom")

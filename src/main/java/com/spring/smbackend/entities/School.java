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
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String description;
    @JsonBackReference
    @OneToMany(mappedBy = "school")
    Collection<Department> departments;
    @JsonBackReference
    @OneToMany(mappedBy = "school")
    Collection<Section> sections;
}
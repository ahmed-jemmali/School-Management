package com.spring.smbackend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Validated
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name must not be null")
    private String name;

    private String address;
    private String description;

    @OneToMany(mappedBy = "school")
    @JsonBackReference
    private Collection<Department> departments;

    @OneToMany(mappedBy = "school")
    @JsonBackReference
    private Collection<Section> sections;
}

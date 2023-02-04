package com.spring.smbackend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name must not be null")
    private String name;

    @OneToMany(mappedBy = "department")
    @JsonBackReference
    private Collection<Hall> halls;

    @NotNull(message = "School must not be null")
    @ManyToOne
    @JoinColumn(name = "school_id", referencedColumnName = "id")
    private School school;
}

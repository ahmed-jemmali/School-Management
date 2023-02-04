package com.spring.smbackend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Validated
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotNull(message = "Name must not be null")
    private String name;

    @OneToMany(mappedBy = "section") //relation Bidirectional // mappedBy = "section" : & so as not to create another relationship
    @JsonBackReference
    private Collection<Classroom> classrooms;

    @NotNull(message = "School must not be null")
    @ManyToOne
    @JoinColumn(name = "school_id", referencedColumnName = "id")
    private School school;
}

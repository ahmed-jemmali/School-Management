package com.spring.smbackend.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Validated
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name must not be null")
    private String name;

    @NotNull(message = "Phone number must not be null")
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull(message = "Email must not be null")
    private String email;

    private String address;

    @NotNull(message = "Description must not be null")
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "teacher_classroom",
            joinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "classroom_id", referencedColumnName = "id")
    )
    @JsonManagedReference
    private Set<Classroom> classrooms;
}

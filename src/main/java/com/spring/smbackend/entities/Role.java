package com.spring.smbackend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sec_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name must not be null")
    private String name;

    @OneToMany(mappedBy = "role")
    @JsonBackReference
    private Collection<AppUser> users;
}

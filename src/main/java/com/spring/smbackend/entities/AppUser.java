package com.spring.smbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "sec_user")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String phoneNumber;

    private String address;

    @NotNull
    private String email;

    @NotNull
    @JsonIgnore
    private String password;

    private Date created;

    @NotNull(message = "Role must not be null")
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    public AppUser(String name, String phoneNumber, String address, String email, String password, Role role) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.password = password;
        this.created = new Date();
        this.role = role;
    }

    public AppUser(Long id) {
        this.id = id;
    }
}

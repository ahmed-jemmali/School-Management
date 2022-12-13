package com.spring.smbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolDto {
    private Long id;
    private String name;
    private String address;
    private String description;
}
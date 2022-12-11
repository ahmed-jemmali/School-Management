package com.spring.smbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HallDto {
    private Long id;
    private String name;
    private String floor;
    private Long departmentId;
}
package com.spring.smbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentModel {
    private Long id;
    private String name;
    private int age;
    private String parentName;
    private String phoneNumber;
    private String email;
    private String address;
    private Long classroomId;
}
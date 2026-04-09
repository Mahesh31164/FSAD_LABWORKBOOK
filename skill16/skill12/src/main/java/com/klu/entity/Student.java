package com.klu.entity;

import jakarta.persistence.*;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Student ID", example = "1")
    private Long id;

    @Schema(description = "Student Name", example = "Mahesh")
    private String name;

    @Schema(description = "Email", example = "mahesh@gmail.com")
    private String email;

    @Schema(description = "Course", example = "CSE")
    private String course;
}
package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class Certification {

    private int id = 101;
    private String name = "Java Full Stack";
    private String dateOfCompletion = "2025";

    public String toString() {
        return "Certification ID: " + id +
                ", Name: " + name +
                ", Date: " + dateOfCompletion;
    }
}
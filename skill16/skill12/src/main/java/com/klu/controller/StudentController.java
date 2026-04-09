package com.klu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klu.entity.Student;
import com.klu.serviceImpl.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/students")
@Tag(name = "Student API", description = "CRUD operations for Student")
public class StudentController {

    @Autowired
    private StudentService service;

    // ✅ GET ALL
    @Operation(summary = "Get all students")
    @ApiResponse(responseCode = "200", description = "Students retrieved successfully")
    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        return ResponseEntity.ok(service.getAllStudents());
    }

    // ✅ GET BY ID
    @Operation(summary = "Get student by ID")
    @ApiResponse(responseCode = "200", description = "Student found")
    @ApiResponse(responseCode = "404", description = "Student not found")
    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getStudentById(id));
    }

    // ✅ ADD
    @Operation(summary = "Add new student")
    @ApiResponse(responseCode = "201", description = "Student created")
    @PostMapping
    public ResponseEntity<Student> add(@RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.addStudent(student));
    }

    // ✅ UPDATE
    @Operation(summary = "Update student")
    @ApiResponse(responseCode = "200", description = "Student updated")
    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id,
                                          @RequestBody Student student) {
        return ResponseEntity.ok(service.updateStudent(id, student));
    }

    // ✅ DELETE
    @Operation(summary = "Delete student")
    @ApiResponse(responseCode = "200", description = "Student deleted")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deleteStudent(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}
package com.klu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klu.entity.Course;
import com.klu.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // Create Course
    @PostMapping("/create")
    public ResponseEntity<?> createCourse(@RequestBody Course course) {

        if(course.getTitle() == null || course.getTitle().isEmpty()) {
            return new ResponseEntity<>("Title cannot be empty", HttpStatus.BAD_REQUEST);
        }

        Course savedCourse = courseService.saveCourse(course);

        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    // Get All Courses
    @GetMapping("/getAllCourses")
    public ResponseEntity<?> getAllCourses() {

        List<Course> courses = courseService.getAllCourses();

        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    // Get Course by ID
    @GetMapping("/getCourse/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id) {

        Course course = courseService.getCourseById(id);

        if(course == null) {
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    // Update Course
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody Course course) {

        Course updatedCourse = courseService.updateCourse(id, course);

        if(updatedCourse == null) {
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }

    // Delete Course
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {

        boolean deleted = courseService.deleteCourse(id);

        if(!deleted) {
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Course deleted successfully", HttpStatus.OK);
    }

    // Search Course by Title
    @GetMapping("/search/{title}")
    public ResponseEntity<?> searchCourses(@PathVariable String title) {

        List<Course> courses = courseService.searchByTitle(title);

        if(courses.isEmpty()) {
            return new ResponseEntity<>("No courses found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(courses, HttpStatus.OK);
    }
}
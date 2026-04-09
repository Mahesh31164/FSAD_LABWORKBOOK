package com.klu.service;

import java.util.List;
import com.klu.entity.Course;

public interface CourseService {

    Course saveCourse(Course course);

    List<Course> getAllCourses();

    Course getCourseById(Long id);

    Course updateCourse(Long id, Course course);

    boolean deleteCourse(Long id);

    List<Course> searchByTitle(String title);
}
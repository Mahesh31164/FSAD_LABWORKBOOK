package com.klu.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.entity.Course;
import com.klu.repo.CourseRepo;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepo courseRepository;

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public Course updateCourse(Long id, Course course) {

        Course existingCourse = courseRepository.findById(id).orElse(null);

        if (existingCourse != null) {
            existingCourse.setTitle(course.getTitle());
            existingCourse.setDuration(course.getDuration());
            existingCourse.setFees(course.getFees());

            return courseRepository.save(existingCourse);
        }

        return null;
    }

    @Override
    public boolean deleteCourse(Long id) {

        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public List<Course> searchByTitle(String title) {

        List<Course> courses = courseRepository.findAll();

        return courses.stream()
                .filter(c -> c.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }
}
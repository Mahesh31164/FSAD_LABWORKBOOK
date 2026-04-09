package com.klu.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klu.entity.Course;

public interface CourseRepo extends JpaRepository<Course, Long>{
	
}

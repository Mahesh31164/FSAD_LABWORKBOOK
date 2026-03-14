package com.klu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.exception.StudentNotFoundException;
import com.klu.model.Student;
import com.klu.repo.StudentRepo;

@Service
public class StudentService {
	@Autowired
	private StudentRepo repo;
	
	public Student addStudent(Student s) {
		return repo.save(s);
	}
	
	public Student getStudentById(Long id) {
		Student student = repo.findById(id).orElseThrow(() ->  new StudentNotFoundException("Student with ID " + id + " not found"));
        return student;
	}
	
	
}

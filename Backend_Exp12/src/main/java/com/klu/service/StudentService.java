package com.klu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.model.Student;
import com.klu.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    public Student addStudent(Student s) {
        return repo.save(s);
    }

    public Student updateStudent(Long id, Student s) {
        Student st = repo.findById(id).orElseThrow();
        st.setName(s.getName());
        st.setEmail(s.getEmail());
        st.setCourse(s.getCourse());
        return repo.save(st);
    }

    public void deleteStudent(Long id) {
        repo.deleteById(id);
    }
}
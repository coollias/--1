package com.example.springbootdemo.service;

import com.example.springbootdemo.pojo.Student;


public interface StudentService {
    void saveStudent(Student student);

    boolean login(String studentId, String password); // Add login method
     Student getStudentById(String studentId);
}

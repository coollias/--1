package com.example.springbootdemo.controller;

// StudentController.java
import com.example.springbootdemo.pojo.Student;
import com.example.springbootdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService; // Uses the interface

    @PostMapping("/students")
    public void addStudent(@RequestBody Student student) { // Use @RequestBody here
        studentService.saveStudent(student);
    }
}


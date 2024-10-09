package com.example.springbootdemo.controller;

import com.example.springbootdemo.pojo.Student;
import com.example.springbootdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Endpoint for a single student
    @PostMapping("/students")
    public void addStudents(@RequestBody List<Student> students) {
        for (Student student : students) {
            studentService.saveStudent(student);
        }
    }


}



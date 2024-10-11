package com.example.springbootdemo.service.impl;

// StudentServiceImpl.java
import com.example.springbootdemo.mapper.StudentMapper;
import com.example.springbootdemo.pojo.Student;
import com.example.springbootdemo.pojo.User;
import com.example.springbootdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class StudentServiceImpl implements StudentService {



    @Autowired
    private StudentMapper studentMapper;

    private static final int PASSWORD_LENGTH = 8;

    @Override
    public void saveStudent(Student student) {
        String generatedPassword = generateRandomPassword(); // Generate the password
        student.setPassword(generatedPassword);
        studentMapper.insertStudent(student.getStudentId(), student.getName(), student.getGender(), student.getIdCardNumber(), student.getPassword());
    }

    // Generate a random password
    private String generateRandomPassword() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[6]; // 6 bytes = 8 base64 characters
        random.nextBytes(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }
    @Override
    public boolean login(String studentId, String password) {
        // Retrieve the student by studentId
        Student student = studentMapper.getStudentById(studentId);
        if (student != null) {
            // Check if the password matches
            return student.getPassword().equals(password);
        }
        return false; // Return false if student is not found or password doesn't match
}
    @Override
    public Student getStudentById(String studentId) {
        Student student=studentMapper.getStudentById(studentId);
        return student;
    }
}

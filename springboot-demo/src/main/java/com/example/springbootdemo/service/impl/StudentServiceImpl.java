package com.example.springbootdemo.service.impl;

// StudentServiceImpl.java
import com.example.springbootdemo.mapper.StudentMapper;
import com.example.springbootdemo.pojo.Student;
import com.example.springbootdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void saveStudent(Student student) {
        studentMapper.insertStudent(student.getStudentId(), student.getName(), student.getGender(), student.getIdCardNumber());
    }
}


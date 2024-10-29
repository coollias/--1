package com.example.springbootdemo.service;


import com.example.springbootdemo.mapper.CourseMapper;
import com.example.springbootdemo.pojo.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseMapper courseMapper;

    // 获取所有课程
    public List<Course> getAllCourses() {
        return courseMapper.findAll();
    }

    // 根据ID获取课程
    public Course getCourseById(String Cid) {
        return courseMapper.findById(Cid);
    }

    // 添加新课程
    public void addCourse(Course course) {
        courseMapper.insert(course);
    }

    // 更新课程信息
    public void updateCourse(Course course) {
        courseMapper.update(course);
    }

    // 根据ID删除课程
    public void deleteCourse(String Cid) {
        courseMapper.delete(Cid);
    }
}



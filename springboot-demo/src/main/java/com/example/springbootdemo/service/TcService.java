package com.example.springbootdemo.service;



import com.example.springbootdemo.mapper.TcMapper;
import com.example.springbootdemo.pojo.Tc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TcService {

    @Autowired
    private TcMapper tcMapper;

    // 获取所有课程
    public List<Tc> getAllCourses() {
        return tcMapper.findAll();
    }

    // 根据 ID 获取课程
    public Tc getCourseById(String cid) {
        return tcMapper.findById(cid);
    }

    // 添加新课程
    public void addCourse(Tc tc) {
        tcMapper.insert(tc);
    }

    // 更新课程
    public void updateCourse(Tc tc) {
        tcMapper.update(tc);
    }

    // 根据 ID 删除课程
    public void deleteCourse(String cid) {
        tcMapper.delete(cid);
    }

    // 更新课程
    public void updateCourseDescription(Tc tc) {
        tcMapper.updatedescriptionbycid(tc);
    }
}


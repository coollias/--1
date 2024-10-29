package com.example.springbootdemo.controller;


import com.example.springbootdemo.pojo.Result;
import com.example.springbootdemo.pojo.Tc;
import com.example.springbootdemo.service.TcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tc/courses")
public class TcController {

    @Autowired
    private TcService tcService;

    // 获取所有课程
    @GetMapping
    public ResponseEntity<List<Tc>> getAllCourses() {
        List<Tc> courses = tcService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    // 根据 ID 获取课程
    @GetMapping("/{cid}")
    public ResponseEntity<Tc> getCourseById(@PathVariable String cid) {
        Tc course = tcService.getCourseById(cid);
        if (course != null) {
            return ResponseEntity.ok(course);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 添加新课程
    @PostMapping
    public ResponseEntity<Void> addCourse(@RequestBody Tc tc) {
        tcService.addCourse(tc);
        return ResponseEntity.status(201).build();
    }

    // 更新课程信息
    @PostMapping("/{cid}")
    public Result<String> updateCourse(@PathVariable String cid, @RequestBody Tc tc) {
        tc.setCid(cid); // 确保更新时使用传入的 cid
        tcService.updateCourseDescription(tc);
        return Result.success(tc.getDescription());
    }

    // 根据 ID 删除课程
    @DeleteMapping("/{cid}")
    public ResponseEntity<Void> deleteCourse(@PathVariable String cid) {
        tcService.deleteCourse(cid);
        return ResponseEntity.noContent().build();
    }

}

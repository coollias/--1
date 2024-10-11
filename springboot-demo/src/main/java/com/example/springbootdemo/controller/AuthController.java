package com.example.springbootdemo.controller;

import com.example.springbootdemo.mapper.StudentMapper;
import com.example.springbootdemo.pojo.LoginRequest;
import com.example.springbootdemo.pojo.Result;
import com.example.springbootdemo.pojo.Student;
import com.example.springbootdemo.service.StudentService;
import com.example.springbootdemo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private StudentService studentService;
    @Autowired
    StudentMapper studentMapper;
    @PostMapping("/login1")
    public Result<String> login(@RequestBody Map<String,String> loginRequest) {
        String username=loginRequest.get("username");
        String password=loginRequest.get("password");
        System.out.println(username);
        boolean success = studentService.login(username, password);
        if (success) {

            Map<String, Object> claims = new HashMap<>();
            claims.put("id",username);

            String token= JwtUtil.genToken(claims);
            return Result.success(token);
        } else {
            return Result.error("密码错误");
        }
    }
    @GetMapping("/test/student/{id}")
    public Student testGetStudent(@PathVariable String id) {

        return studentMapper.getStudentById(id);
    }
}

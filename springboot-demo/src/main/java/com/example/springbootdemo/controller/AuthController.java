package com.example.springbootdemo.controller;

import com.example.springbootdemo.mapper.StudentMapper;
import com.example.springbootdemo.mapper.User1Mapper;
import com.example.springbootdemo.pojo.LoginRequest;
import com.example.springbootdemo.pojo.Result;
import com.example.springbootdemo.pojo.Student;
import com.example.springbootdemo.service.StudentService;
import com.example.springbootdemo.service.User1Service;
import com.example.springbootdemo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.springbootdemo.service.StudentService;
import com.example.springbootdemo.pojo.User;

import java.util.HashMap;
import java.util.Map;
//前端链接
@RestController
public class AuthController {
    @Autowired
    private User1Service user1Service;
    @Autowired
    User1Mapper user1Mapper;
    @PostMapping("/login1")
    public Result<String> login(@RequestBody Map<String,String> loginRequest) {
        String username=loginRequest.get("username");
        String password=loginRequest.get("password");
        System.out.println(username);
        boolean success = user1Service.login(username, password);
        if (success) {
            User user= user1Service.getUserById(username);
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",username);
            claims.put("identity",Integer.toString(user.getIdentity()));
            System.out.println(username);
            String token= JwtUtil.genToken(claims);
            return Result.success(token);
        } else {
            return Result.error("密码错误");
        }
    }
    @GetMapping("/test/student/{id}")
    public User testGetStudent(@PathVariable String id) {

        return user1Mapper.findById(id);
    }
}

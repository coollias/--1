package com.example.springbootdemo.service;

import com.example.springbootdemo.mapper.User1Mapper;
import com.example.springbootdemo.pojo.Student;
import com.example.springbootdemo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class User1Service {

    @Autowired
    private User1Mapper user1Mapper;

    // 根据 ID 获取用户
    public User getUserById(String id) {
        return user1Mapper.findById(id);
    }

    // 获取所有用户
    public List<User> getAllUsers() {
        return user1Mapper.findAll();
    }

    // 添加新用户
    public void addUser(User user) {
        user1Mapper.insert(user);
    }

    // 更新用户信息
    public void updateUser(User user) {
        user1Mapper.update(user);
    }

    // 根据 ID 删除用户
    public void deleteUser(int id) {
        user1Mapper.delete(id);
    }

    // 根据身份获取用户
    public List<User> getUsersByIdentity(int identity) {
        return user1Mapper.findByIdentity(identity);
    }

    public boolean login(String studentId, String password) {
        // Retrieve the student by studentId
        System.out.println(studentId);
        System.out.println(password);
        User user= user1Mapper.findById(studentId);
        if (user != null) {
            // Check if the password matches
            return user.getPassword().equals(password);
        }
        return false; // Return false if student is not found or password doesn't match
    }
}

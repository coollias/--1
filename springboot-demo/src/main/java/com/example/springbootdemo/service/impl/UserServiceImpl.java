package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.mapper.UserMapper;
import com.example.springbootdemo.pojo.User;
import com.example.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUserName(String username) {
        User u=userMapper.findByUserName(username);
        return u;
    }

    @Override
    public void register(String username, String password) {
        userMapper.add(username,password);

    }
}

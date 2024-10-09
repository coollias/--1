package com.example.springbootdemo.service;

import com.example.springbootdemo.pojo.User;

public interface UserService {
    //用户名查询
    User findByUserName(String username);

    //注册
    void register(String username, String password);
}

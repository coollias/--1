package com.example.springbootdemo.controller;

import com.example.springbootdemo.pojo.Result;
import com.example.springbootdemo.pojo.User;
import com.example.springbootdemo.service.UserService;
import com.example.springbootdemo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(String username,String password){
        //查询用户
        User u=userService.findByUserName(username);
        if(u==null){
            //没有占用
            //完成注册
            userService.register(username,password);
            return Result.success();
        }
        else{
            return Result.error("用户名已经存在");
        }
    }

    @PostMapping("/login")
    public Result<String> login(String username,String password){
        //查询用户
        User loginUser=userService.findByUserName(username);
        if(loginUser == null){
            return Result.error("用户不存在");
        }
        if(password.equals(loginUser.getPassword())){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",loginUser.getId());
            claims.put("username",loginUser.getUsername());
            String token=JwtUtil.genToken(claims);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo(@RequestHeader(name="Authorization") String token){
        Map<String,Object> map=JwtUtil.parseToken(token);
        String username= (String) map.get("username");

        User user=userService.findByUserName(username);
        return Result.success(user);
    }
}

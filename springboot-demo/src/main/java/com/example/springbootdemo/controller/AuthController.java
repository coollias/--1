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
import com.example.springbootdemo.controller.TokenBlacklistService;

import java.util.HashMap;
import java.util.Map;
//前端链接
@RestController
public class AuthController {


    // 引入 TokenBlacklistService
    @Autowired
    private TokenBlacklistService tokenBlacklistService;

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

    @PostMapping("/updatePassword")
    public Result<String> updatePassword(@RequestHeader(name = "Authorization") String token,
                                         @RequestBody Map<String, String> passwordRequest) {

        // 检查 token 是否在黑名单中
        if (tokenBlacklistService.isTokenInBlacklist(token)) {
            return Result.error("Token 已失效，请重新登录");
        }
        // 从 token 中解析出用户 ID
        Map<String, Object> map = JwtUtil.parseToken(token);
        String username = (String) map.get("id");
        System.out.println("本用户为"+username);

        // 获取原密码和新密码
        String oldPasswd = passwordRequest.get("oldPasswd");
        String newPasswd = passwordRequest.get("newPasswd");

        // 查询用户信息
        User user = user1Service.getUserById(username);
        if (user == null) {
            return Result.error("用户不存在");
        }

        // 验证原密码是否正确
        if (!user.getPassword().equals(oldPasswd)) {
            return Result.error("原密码错误"+oldPasswd);
        }

        // 更新密码
        user.setPassword(newPasswd);
        boolean success = user1Service.updateUserPassword(user);

        if (success) {
            tokenBlacklistService.addTokenToBlacklist(token);
            return Result.success("密码修改成功");
        } else {
            return Result.error("密码修改失败");
        }
    }

}

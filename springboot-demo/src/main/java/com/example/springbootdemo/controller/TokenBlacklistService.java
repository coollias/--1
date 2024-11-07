package com.example.springbootdemo.controller;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;
import java.util.HashSet;
import java.util.Set;
// 通过 @Service 注解声明为 Spring 管理的服务类
@Service
public class TokenBlacklistService {

    // 使用 Set 存储黑名单，快速查找
    private Set<String> blacklist = new HashSet<>();

    // 将 token 加入黑名单
    public void addTokenToBlacklist(String token) {
        // 把失效的 token 加入黑名单
        blacklist.add(token);
        System.out.println("Token added to blacklist: " + token);
    }

    // 检查 token 是否在黑名单中
    public boolean isTokenInBlacklist(String token) {
        // 判断传入的 token 是否在黑名单中
        return blacklist.contains(token);
    }

    // 清空黑名单（可以定期清理过期的 token）
    public void clearBlacklist() {
        blacklist.clear();
    }
}


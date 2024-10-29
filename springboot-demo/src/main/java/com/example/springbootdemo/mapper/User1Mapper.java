package com.example.springbootdemo.mapper;


import com.example.springbootdemo.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface User1Mapper {

    @Select("SELECT * FROM users WHERE id = #{id}")
    User findById(String id);  // 根据 ID 查询用户

    @Select("SELECT * FROM users")
    List<User> findAll();  // 查询所有用户

    @Insert("INSERT INTO users(password, identity) VALUES(#{password}, #{identity})")
    void insert(User user);  // 插入新用户

    @Update("UPDATE users SET password = #{password}, identity = #{identity} WHERE id = #{id}")
    void update(User user);  // 更新用户信息

    @Delete("DELETE FROM users WHERE id = #{id}")
    void delete(int id);  // 根据 ID 删除用户

    @Select("SELECT * FROM users WHERE identity = #{identity}")
    List<User> findByIdentity(int identity);  // 根据身份查询用户
}


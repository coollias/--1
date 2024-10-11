package com.example.springbootdemo.pojo;

public class LoginRequest {
    private String username;
    private String password;

    // Getters and Setters
    public String getStudentId() {
        System.out.println(username);
        return username;
    }

    public void setStudentId(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


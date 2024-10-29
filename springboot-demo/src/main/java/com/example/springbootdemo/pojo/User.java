package com.example.springbootdemo.pojo;

public class User {
   private String id;
   private String password;
   private int identity;


    // Getters and Setters
    public String getId()
    {
        return this.id;
    }
    public int getIdentity()
    {
        return this.identity;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}

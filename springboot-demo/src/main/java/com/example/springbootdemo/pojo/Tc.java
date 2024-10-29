package com.example.springbootdemo.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Tc implements Serializable {
    private String tid;
    private String cid;
    private String description;
}




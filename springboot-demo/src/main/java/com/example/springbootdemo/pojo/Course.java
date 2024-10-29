package com.example.springbootdemo.pojo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class Course implements Serializable {
    private String Cname;  //
    private String Cid;  //
    private String credits;  //
    private String startTime;
}

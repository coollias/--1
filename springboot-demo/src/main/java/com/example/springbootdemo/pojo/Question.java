package com.example.springbootdemo.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Question implements Serializable {
    private int questionId;
    private String content;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;
    private double scorePercentage;  // New field for score percentage

    // No need for additional constructors if you're using Lombok @Data annotation
}

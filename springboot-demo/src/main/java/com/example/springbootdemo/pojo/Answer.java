package com.example.springbootdemo.pojo;

import lombok.Data;

@Data
public class Answer {
    private String userId;  // 用户ID
    private int examId;  // 考试ID
    private int questionId;  // 问题ID
    private String selectedAnswer;  // 用户选择的答案
    private byte isCorrect;  // 是否正确，1 为正确，0 为错误
}

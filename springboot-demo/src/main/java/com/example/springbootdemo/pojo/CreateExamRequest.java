package com.example.springbootdemo.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;
@Data
public class CreateExamRequest {
    private String title;
    private String description;
    private String courseId;
    private Date startTime;
    private Date endTime;
    private List<QuestionRequest> questions;

    // Getters and Setters
    @Data
    public static class QuestionRequest {
        private Integer questionId;
        private String content;
        private Map<String, String> options;
        private String answer;
        private Float percentage;

        // Getters and Setters
    }
}

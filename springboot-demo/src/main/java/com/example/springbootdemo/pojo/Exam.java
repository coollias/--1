package com.example.springbootdemo.pojo;

import java.io.Serializable;
import java.util.Date;

public class Exam implements Serializable {

    private int examId;          // Exam ID (Primary Key)
    private String name;         // Exam name
    private String description;  // Description of the exam
    private Date startTime;      // Start time of the exam
    private Date endTime;        // End time of the exam
    private String createdBy;    // User ID of the person who created the exam

    // Getters and setters

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "examId=" + examId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}

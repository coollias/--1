package com.example.springbootdemo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ExamCourseMapper {

    @Insert("INSERT INTO exam_courses (exam_id, course_id) VALUES (#{examId}, #{courseId})")
    void insertExamCourseLink(@Param("examId") int examId, @Param("courseId") String courseId);
}

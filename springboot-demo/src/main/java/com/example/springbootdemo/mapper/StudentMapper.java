package com.example.springbootdemo.mapper;

import com.example.springbootdemo.pojo.Student;
import org.apache.ibatis.annotations.*;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StudentMapper {

    @Insert("INSERT INTO student (student_id, name, gender, id_card_number, password) VALUES (#{studentId}, #{name}, #{gender}, #{idCardNumber}, #{password})")
    void insertStudent(@Param("studentId") String studentId,
                       @Param("name") String name,
                       @Param("gender") String gender,
                       @Param("idCardNumber") String idCardNumber,
                       @Param("password") String password);


        @Select("SELECT * FROM student WHERE student_id = #{studentId}")
        Student getStudentById(@Param("studentId") String studentId);

    }

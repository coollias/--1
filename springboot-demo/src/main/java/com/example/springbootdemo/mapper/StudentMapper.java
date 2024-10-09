package com.example.springbootdemo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StudentMapper {

    @Insert("INSERT INTO student (student_id, name, gender, id_card_number) VALUES (#{studentId}, #{name}, #{gender}, #{idCardNumber})")
    void insertStudent(@Param("studentId") String studentId,
                       @Param("name") String name,
                       @Param("gender") String gender,
                       @Param("idCardNumber") String idCardNumber);
}


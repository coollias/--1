package com.example.springbootdemo.mapper;

import com.example.springbootdemo.pojo.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseMapper {

    // 查询所有课程
    @Select("SELECT * FROM courses")
    List<Course> findAll();

    // 根据ID查询课程
    @Select("SELECT * FROM courses WHERE Cid = #{Cid}")
    Course findById(String Cid);

    // 插入新课程
    @Insert("INSERT INTO courses(Tid, Cid, discription) VALUES(#{Tid}, #{Cid}, #{discription})")
    void insert(Course course);

    // 更新课程信息
    @Update("UPDATE courses SET credits = #{credits}, cname = #{cname} WHERE Cid = #{Cid}")
    void update(Course course);

    // 根据ID删除课程
    @Delete("DELETE FROM courses WHERE Cid = #{Cid}")
    void delete(String Cid);

    @Select("select * from courses where Cid=#{courseId}")
    Course findCoursesByCids(String courseId);

    @Select("select Cid from sc where Sid=#{id}")
    List<String> getCidsBySid(String id);

    @Select("select Cid from tc where Tid=#{id}")
    List<String> getCidsByTid(String id);

    @Select("select * from courses where Cid=#{courseId}")
    Course findCoursesByTids(String courseId);
    //获取tc表的description
    @Select("SELECT description FROM tc WHERE cid = #{cid}")
    String getDescriptionByCid(@Param("cid") String cid);
}


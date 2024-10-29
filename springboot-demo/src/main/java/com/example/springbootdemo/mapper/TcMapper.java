package com.example.springbootdemo.mapper;


import com.example.springbootdemo.pojo.Tc;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

@Mapper
public interface TcMapper {
    // 查询所有课程
    @Select("SELECT * FROM tc")
    List<Tc> findAll();

    // 根据 ID 查询课程
    @Select("SELECT * FROM tc WHERE cid = #{cid}")
    Tc findById(String cid);

    // 插入新课程
    @Insert("INSERT INTO tc (cid, tid, description) VALUES (#{cid}, #{tid}, #{description})")
    void insert(Tc tc);

    // 更新课程信息
    @Update("UPDATE tc SET tid = #{tid}, description = #{description} WHERE cid = #{cid}")
    void update(Tc tc);

    // 根据 ID 删除课程
    @Delete("DELETE FROM tc WHERE cid = #{cid}")
    void delete(String cid);
    // 更新课程信息
    @Update("UPDATE tc SET  description = #{description} WHERE cid = #{cid}")
    void updatedescriptionbycid(Tc tc);
}


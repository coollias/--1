package com.example.springbootdemo.mapper;

import com.example.springbootdemo.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper

public interface ArticleMapper {
    //新增
    @Insert("insert into article(title,content,state,create_user,create_time,update_time,goal_people)"+
            "values(#{title},#{content},#{state},#{createUser},#{createTime},#{updateTime},#{goalPeople})")
    void add(Article article);

    //查询列表
    @Select("select * from article where create_user =#{userId}")
    List<Article> list(Integer userId);

    //id查询细节
    @Select("select * from article where id=#{id}")
    Article findById(Integer id);

    //更新
    @Update("update article set title=#{title},content=#{content},update_time=#{updateTime},goal_people=#{goalPeople} where id=#{id}")
    void update(Article article);

    @Delete("delete from article where id=#{id}")
    void delete(Integer id);

    @Select("select * from article where goal_people =#{username}")
    List<Article> signlist(String username);

    @Update("update article set update_time=#{updateTime},mode=#{mode} where id=#{id}")
    void sign(Article article);

    @Select("select * from article where mode =1")
    List<Article> signlist1(String username);
}

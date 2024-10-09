package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.mapper.ArticleMapper;
import com.example.springbootdemo.pojo.Article;
import com.example.springbootdemo.service.ArticleService;
import com.example.springbootdemo.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public void add(Article article) {
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        Map<String,Object> map=ThreadLocalUtil.get();
        Integer userId= (Integer) map.get("id");
        article.setCreateUser(userId);
        articleMapper.add(article);
    }

    @Override
    public List<Article> list() {
        Map<String,Object> map=ThreadLocalUtil.get();
        Integer userId= (Integer) map.get("id");
//        System.out.println(articleMapper.list(userId));
        return articleMapper.list(userId);
    }

    @Override
    public Article findById(Integer id) {
        Article A=articleMapper.findById(id);
        return A;
    }

    @Override
    public void update(Article article) {
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.update(article);
    }

    @Override
    public void delete(Integer id) {
        articleMapper.delete(id);
    }

    @Override
    public List<Article> signlist() {
        Map<String,Object> map=ThreadLocalUtil.get();
        String username= (String) map.get("username");
        return articleMapper.signlist(username);
    }

    @Override
    public void sign(Article article) {
        article.setMode(1);
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.sign(article);
    }

    @Override
    public List<Article> signlist1() {
        Map<String,Object> map=ThreadLocalUtil.get();
        String username= (String) map.get("username");
        return articleMapper.signlist1(username);
    }


}

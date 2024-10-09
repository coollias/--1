package com.example.springbootdemo.service;

import com.example.springbootdemo.pojo.Article;

import java.util.List;

public interface ArticleService {
    void add(Article article);

    List<Article> list();

    Article findById(Integer id);

    void update(Article article);

    void delete(Integer id);

    List<Article> signlist();

    void sign(Article article);

    List<Article> signlist1();
}

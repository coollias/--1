package com.example.springbootdemo.controller;

import com.example.springbootdemo.pojo.Article;
import com.example.springbootdemo.pojo.Result;
import com.example.springbootdemo.service.ArticleService;
import com.example.springbootdemo.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/deal")
public class DealController {
    //    @GetMapping("/list")
//    public Result<String> list(/*@RequestHeader(name="Authorization") String token, HttpServletResponse response*/) {
////        try {
////            Map<String,Object> claims=JwtUtil.parseToken(token);
////            return Result.success("欣喜");
////        } catch (Exception e) {
////            response.setStatus(401);
////            return Result.error("未登录");
////        }
//        return Result.success("欣喜");
//
//    }
    @Autowired
    private ArticleService articleService;

    @PostMapping("/add")
    public Result add(@RequestBody Article article) {
        articleService.add(article);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<Article>> List() {
        List<Article> ar = articleService.list();
        return Result.success(ar);
    }

    @GetMapping("/detail")
    public Result<Article> detail(Integer id) {
        Article A = articleService.findById(id);
        return Result.success(A);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Article article) {
        articleService.update(article);
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result delete(Integer id) {
        articleService.delete(id);
        return Result.success();
    }

    @GetMapping("/signlist")
    public Result<List<Article>> SignList() {
        List<Article> ar = articleService.signlist();
        return Result.success(ar);
    }

    @PutMapping("/sign")
    public Result sign(@RequestBody Article article) {
        articleService.sign(article);
        return Result.success();
    }

//    @GetMapping("/signlist1")
//    public Result<List<Article>> SignList1() {
//        List<Article> ar = articleService.signlist1();
//        return Result.success(ar);
//    }
}

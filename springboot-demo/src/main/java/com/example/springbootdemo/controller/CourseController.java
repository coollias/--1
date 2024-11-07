package com.example.springbootdemo.controller;


import com.example.springbootdemo.mapper.CourseMapper;
import com.example.springbootdemo.pojo.Course;
import com.example.springbootdemo.pojo.Result;
import com.example.springbootdemo.service.CourseService;
import com.example.springbootdemo.service.FileUpLoadService;
import com.example.springbootdemo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.springbootdemo.pojo.Result;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courses")
public class CourseController {
    // 引入 TokenBlacklistService
    @Autowired
    private TokenBlacklistService tokenBlacklistService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private FileUpLoadService FileUpLoadService;
    @Autowired
    private CourseMapper courseMapper;

    // 获取所有课程
    @GetMapping
    public List<Course> getAllCourses(@RequestHeader(name="Authorization") String token) {

        // 检查 token 是否在黑名单中
        if (tokenBlacklistService.isTokenInBlacklist(token)) {
            return null;
        }

        Map<String,Object> map= JwtUtil.parseToken(token);
        String identity= map.get("identity").toString();
        String id= (String) map.get("id");
        if(identity.equals("1"))//学生
        {
            // 根据 Sid 查找对应的 cid 列表
            List<String> courseIds = courseMapper.getCidsBySid(id);
            System.out.println(courseIds);
            // 根据 cid 列表查询课程信息
            List<Course> returns=new ArrayList<>();;
            for (String courseId : courseIds) {
                Course courses = courseMapper.findCoursesByCids(courseId);
                returns.add(courses);
            }

            // 返回找到的课程列表
            return returns;
        }
        else if(identity.equals("2"))//老师
        {

            // 根据 Tid 查找对应的 Cid 列表
            List<String> courseIds = courseMapper.getCidsByTid(id);System.out.println(courseIds);
            List<Course> returns=new ArrayList<>();;
            // 根据 cid 列表查询课程信息
            for (String courseId : courseIds) {
                Course courses = courseMapper.findCoursesByTids(courseId);
                returns.add(courses);
            }
            // 返回找到的课程列表
            return returns;
        }
        return courseService.getAllCourses();
    }

//    @PostMapping
//
//    public ResponseEntity<String> addCourse(@RequestHeader(name="Authorization") String token,
//                                            @RequestParam("file") MultipartFile file,
//                                            @RequestParam("course") String courseJson) {
//        try {
//            // 解析 JSON 字符串为 Course 对象
//            ObjectMapper objectMapper = new ObjectMapper();
//            Course course = objectMapper.readValue(courseJson, Course.class);
//
//            // 上传文件
//            String imageUrl = FileUpLoadService.uploadFile(file);
//            course.setImageUrl(imageUrl);
//
//            // 添加课程
//            courseService.addCourse(course);
//            return ResponseEntity.status(HttpStatus.CREATED).build();
//        } catch (Exception e) {
//            e.printStackTrace(); // 打印异常信息
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }
//    }
@GetMapping("/{Cid}")
public ResponseEntity<Map<String, Object>> getCourseById(@RequestHeader(name="Authorization") String token,@PathVariable String Cid) {
    // 检查 token 是否在黑名单中
    if (tokenBlacklistService.isTokenInBlacklist(token)) {
        return null;
    }
    Course course = courseService.getCourseById(Cid);
    // 根据课程ID获取描述
    String description = courseMapper.getDescriptionByCid(Cid);

    // 创建返回结果
    Map<String, Object> courseanddescription = new HashMap<>();
    courseanddescription.put("course", course);
    courseanddescription.put("description", description);

    return ResponseEntity.ok(courseanddescription);
}






    // 更新课程信息
    @PutMapping("/{Cid}")
    public ResponseEntity<Void> updateCourse(@RequestHeader(name="Authorization") String token,@PathVariable String Cid, @RequestBody Course course) {
        // 检查 token 是否在黑名单中
        if (tokenBlacklistService.isTokenInBlacklist(token)) {
            return null;
        }
        course.setCid(Cid);  // 设置课程ID以更新
        courseService.updateCourse(course);
        return ResponseEntity.ok().build();
    }

    // 删除课程
    @DeleteMapping("/{Cid}")
    public ResponseEntity<Void> deleteCourse(@RequestHeader(name="Authorization") String token,@PathVariable String Cid) {
        // 检查 token 是否在黑名单中
        if (tokenBlacklistService.isTokenInBlacklist(token)) {
            return null;
        }
        courseService.deleteCourse(Cid);
        return ResponseEntity.ok().build();
    }
}


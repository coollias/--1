package com.example.springbootdemo.controller;

import com.example.springbootdemo.mapper.ExamMapper;
import com.example.springbootdemo.pojo.CreateExamRequest;
import com.example.springbootdemo.pojo.Exam;
import com.example.springbootdemo.pojo.Question;
import com.example.springbootdemo.service.ExamService;
import com.example.springbootdemo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/exams")
public class ExamController
{

    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    @Autowired
    private ExamService examService;

    @Autowired
    private ExamMapper examMapper;


    // 获取学生的考试列表
    @GetMapping
    public ResponseEntity<List<Exam>> getExams(@RequestHeader(name="Authorization") String token) {

        // 检查 token 是否在黑名单中
        if (tokenBlacklistService.isTokenInBlacklist(token)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        // 解析 token 获取用户身份和 id
        Map<String, Object> map = JwtUtil.parseToken(token);
        String identity = map.get("identity").toString();
        String userId = (String) map.get("id");

        List<Exam> exams = new ArrayList<>();

        if (identity.equals("1")) {  // 学生身份
            // 根据 Sid 查找该学生对应的课程
            List<String> courseIds = examMapper.getCourseIdsBySid(userId);
            for (String courseId : courseIds) {
                // 根据课程ID查找考试信息
                List<Exam> examList = examMapper.findExamsByCourseId(courseId); // Changed to return a list
                exams.addAll(examList); // Add all the exams for this course to the list
            }
        } else if (identity.equals("2")) {  // 老师身份
            // 根据 Tid 查找该老师教授的课程
            List<String> courseIds = examMapper.getCourseIdsByTid(userId);
            for (String courseId : courseIds) {
                // 根据课程ID查找考试信息
                List<Exam> examList = examMapper.findExamsByCourseId(courseId); // Changed to return a list
                exams.addAll(examList); // Add all the exams for this course to the list
            }
        } else {
            // 如果是管理员等角色，返回所有考试
            exams = examService.getAllExams();
        }

        return ResponseEntity.ok(exams);
    }


    // 根据考试ID查询具体考试详情
    @GetMapping("/{examId}")
    public ResponseEntity<Exam> getExamById(@RequestHeader(name="Authorization") String token,
                                            @PathVariable String examId) {

        // 检查 token 是否在黑名单中
        if (tokenBlacklistService.isTokenInBlacklist(token)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        // 获取考试详情
        Exam exam = examService.getExamById(examId);
        if (exam == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(exam);
    }
    // Get questions associated with a specific exam
    @GetMapping("/{examId}/questions")
    public ResponseEntity<List<Map<String, Object>>> getQuestionsByExamId(@RequestHeader(name="Authorization") String token,
                                                                          @PathVariable int examId) {
        // Check if the token is in the blacklist
        if (tokenBlacklistService.isTokenInBlacklist(token)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        // Fetch the questions for the given exam
        List<Question> questions = examMapper.findQuestionsByExamId(examId);

        List<Map<String, Object>> questionDataList = new ArrayList<>();
        for (Question question : questions) {
            Map<String, Object> questionData = new HashMap<>();
            questionData.put("question_id", question.getQuestionId());
            questionData.put("content", question.getContent());
            questionData.put("options", Map.of(
                    "A", question.getOptionA(),
                    "B", question.getOptionB(),
                    "C", question.getOptionC(),
                    "D", question.getOptionD()
            ));
            questionData.put("answer", question.getCorrectAnswer());
            questionData.put("percentage", question.getScorePercentage());
            questionDataList.add(questionData);
        }

        return ResponseEntity.ok(questionDataList);
    }

    // 提交考试答案
    @PostMapping("/{examId}/submit")
    public ResponseEntity<String> submitExamAnswers(
            @RequestHeader(name="Authorization") String token,
            @PathVariable("examId") int examId,
            @RequestBody List<Map<String, Object>> answersFromFrontend) {

        try {
            // 检查 token 是否在黑名单中
            if (tokenBlacklistService.isTokenInBlacklist(token)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

            // 解析 token 获取用户身份和 id
            Map<String, Object> map = JwtUtil.parseToken(token);
            String identity = map.get("identity").toString();
            String userId = (String) map.get("id");
            // 你可以根据需要的方式获取用户ID

            // 调用ExamService的处理方法，进行答案判断和保存
            int score = examService.checkAnswersAndSave(userId, examId, answersFromFrontend);

            // 返回成功响应
            return ResponseEntity.ok(""+score);
        } catch (Exception e) {
            // 处理异常并返回错误响应
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while submitting answers");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createExam(
            @RequestHeader(name = "Authorization") String token,  // 从请求头获取token
            @RequestBody CreateExamRequest createExamRequest) {
        try {
            // 1. 解析 token 获取用户身份和 ID
            Map<String, Object> map = JwtUtil.parseToken(token);
            String userId = (String) map.get("id");  // 从token中获取用户ID
            // 你可以根据需要使用 userId 进行其他处理，或者记录创建考试的用户

            // 2. 创建试卷
            Exam exam = new Exam();
            exam.setName(createExamRequest.getTitle());
            exam.setDescription(createExamRequest.getDescription());
            exam.setStartTime(createExamRequest.getStartTime());
            exam.setEndTime(createExamRequest.getEndTime());
            exam.setCreatedBy(userId);  // 使用从token中获得的用户ID作为创建者
            int examId = examService.createExam(exam);

            // 3. 创建题目
            for (CreateExamRequest.QuestionRequest questionRequest : createExamRequest.getQuestions()) {
                Question question = new Question();
                question.setContent(questionRequest.getContent());
                question.setOptionA(questionRequest.getOptions().get("A"));
                question.setOptionB(questionRequest.getOptions().get("B"));
                question.setOptionC(questionRequest.getOptions().get("C"));
                question.setOptionD(questionRequest.getOptions().get("D"));
                question.setCorrectAnswer(questionRequest.getAnswer());
                question.setScorePercentage(questionRequest.getPercentage());

                // 4. 保存题目
                examService.saveQuestion(question);

                // 5. 关联试卷和题目
                examService.linkExamAndQuestion(examId, question.getQuestionId());
            }

            // 6. 关联试卷和课程
            examService.linkExamAndCourse(examId, createExamRequest.getCourseId());

            return ResponseEntity.status(HttpStatus.CREATED).body("Exam created successfully!");
        } catch (Exception e) {
            e.printStackTrace(); // 打印堆栈信息
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating exam: " + e.getMessage());

        }
    }


}



package com.example.springbootdemo.service;

import com.example.springbootdemo.mapper.ExamCourseMapper;
import com.example.springbootdemo.mapper.ExamMapper;
import com.example.springbootdemo.mapper.QuestionMapper;
import com.example.springbootdemo.pojo.Answer;
import com.example.springbootdemo.pojo.Exam;
import com.example.springbootdemo.pojo.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ExamService {

    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private ExamCourseMapper examCourseMapper;

    // 获取所有考试
    public List<Exam> getAllExams() {
        return examMapper.findAll();
    }

    // 根据ID获取考试详情
    public Exam getExamById(String examId) {
        return examMapper.findById(examId);
    }

    // 检查用户提交的答案并保存，同时计算分数
    public int checkAnswersAndSave(String userId, int examId, List<Map<String, Object>> answersFromFrontend) {
        int totalScore = 0; // 用于记录总分

        // 遍历前端传来的答案数组
        for (Map<String, Object> answerData : answersFromFrontend) {
            Integer questionId = (Integer) answerData.get("question_id");
            String selectedAnswer = (String) answerData.get("answer");

            // 查询数据库，获取当前题目的正确答案和分数
            String correctAnswer = examMapper.getCorrectAnswerByQuestionId(questionId);
            Float scorePercentage = examMapper.getQuestionScoreById(questionId); // 假设你能获取每题的得分百分比

            // 判断答案是否正确
            boolean isCorrect = selectedAnswer.equals(correctAnswer);

            // 如果答案正确，累加分数
            if (isCorrect) {
                totalScore += scorePercentage; // 累加该题的得分
            }

            // 将答案和是否正确保存到数据库
            Answer answer = new Answer();
            answer.setUserId(userId);
            answer.setExamId(examId);
            answer.setQuestionId(questionId);
            answer.setSelectedAnswer(selectedAnswer);
            answer.setIsCorrect(isCorrect ? (byte) 1 : (byte) 0);

            // 保存答案到数据库
            examMapper.saveUserAnswer(answer);
        }

        // 返回总分
        return totalScore;
    }
    // 创建试卷
    public int createExam(Exam exam) {
        // 1. 插入试卷信息
        examMapper.insertExam(exam);
        return exam.getExamId();
    }

    // 保存题目
    public void saveQuestion(Question question) {
        // 2. 插入题目信息
        questionMapper.insertQuestion(question);
    }

    // 关联试卷和题目
    public void linkExamAndQuestion(int examId, int questionId) {
        // 3. 关联试卷与题目
        examMapper.linkExamAndQuestion(examId, questionId);
    }

    // 关联试卷和课程
    public void linkExamAndCourse(int examId, String courseId) {
        // 4. 关联试卷与课程
        examCourseMapper.insertExamCourseLink(examId, courseId);
    }

}

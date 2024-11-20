package com.example.springbootdemo.mapper;

import com.example.springbootdemo.pojo.Answer;
import com.example.springbootdemo.pojo.Exam;
import com.example.springbootdemo.pojo.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ExamMapper {

    // 查询所有考试
    @Select("SELECT * FROM exams")
    List<Exam> findAll();

    // 根据examId查找考试
    @Select("SELECT * FROM exams WHERE exam_id = #{examId}")
    Exam findById(String examId);

    // 根据Sid查找该学生对应的所有课程
    @Select("SELECT Cid FROM sc WHERE Sid = #{sid}")
    List<String> getCourseIdsBySid(String sid);

    // 根据Tid查找该老师教授的所有课程
    @Select("SELECT Cid FROM tc WHERE Tid = #{tid}")
    List<String> getCourseIdsByTid(String tid);

    // 根据课程ID查找对应的考试
    @Select("SELECT e.* FROM exams e JOIN exam_courses ec ON e.exam_id = ec.exam_id WHERE ec.course_id = #{courseId}")
    List<Exam> findExamsByCourseId(String courseId);

    //    // 根据考试ID查找所有相关课程
//    @Select("SELECT c.* FROM courses c JOIN exam_courses ec ON c.Cid = ec.course_id WHERE ec.exam_id = #{examId}")
//    List<Course> findCoursesByExamId(String examId);
    // 获取问题的正确答案
    @Select("SELECT correct_answer FROM questions WHERE question_id = #{questionId}")
    String getCorrectAnswerByQuestionId(int questionId);

    // 保存用户答案
    @Insert("INSERT INTO answers(user_id, exam_id, question_id, selected_answer, is_correct) " +
            "VALUES(#{userId}, #{examId}, #{questionId}, #{selectedAnswer}, #{isCorrect})")
    void saveUserAnswer(Answer answer);
    // Query to fetch all questions for a specific exam

    @Select("SELECT e.* FROM questions e JOIN exam_questions ec ON e.question_id = ec.question_id WHERE ec.exam_id = #{examId}")
    List<Question> findQuestionsByExamId(int examId);

    // 获取题目的得分百分比
    @Select("SELECT score_percentage FROM questions WHERE question_id = #{questionId}")
    Float getQuestionScoreById(Integer questionId);
}

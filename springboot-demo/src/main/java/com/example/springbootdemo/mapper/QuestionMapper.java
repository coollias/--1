package com.example.springbootdemo.mapper;
import com.example.springbootdemo.pojo.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface QuestionMapper
{

    @Insert("INSERT INTO questions (content, option_a, option_b, option_c, option_d, correct_answer, score_percentage) " +
            "VALUES (#{content}, #{optionA}, #{optionB}, #{optionC}, #{optionD}, #{correctAnswer}, #{scorePercentage})")
    @Options(useGeneratedKeys = true, keyProperty = "questionId")
    void insertQuestion(Question question);
}

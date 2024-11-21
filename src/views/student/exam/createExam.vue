<template>
    <div>
      <p>{{ examId }}</p>
      <!-- 试卷标题和描述 -->
      <el-input v-model="examTitle" placeholder="请输入试卷标题"></el-input>
      <br><br>
      <el-input type="textarea" v-model="examDescription" placeholder="请输入试卷描述"></el-input>
  
      <!-- 选择课程 -->
      <el-select v-model="selectedCourseId" placeholder="请选择试卷所属课程">
        <el-option v-for="course in courses" :key="course.cid" :label="course.cname" :value="course.cid"></el-option>
      </el-select>
      <br><br>
  
      <!-- 考试开始时间和结束时间 -->
      <el-date-picker
        v-model="startTime"
        type="datetime"
        placeholder="选择考试开始时间"
        format="YYYY-MM-DDTHH:mm:ss"
      ></el-date-picker>
      <br><br>
      <el-date-picker
        v-model="endTime"
        type="datetime"
        placeholder="选择考试结束时间"
        format="YYYY-MM-DDTHH:mm:ss"
      ></el-date-picker>
      <br><br>
  
      <!-- 试题列表展示 -->
      <el-table :data="questions" style="width: 100%">
        <el-table-column prop="content" label="试题内容"></el-table-column>
        <el-table-column prop="answer" label="正确答案"></el-table-column>
        <el-table-column prop="percentage" label="占比 (%)"></el-table-column>
        <el-table-column label="操作">
          <template #default="scope">
            <el-button type="primary" @click="editQuestion(scope.row)">编辑</el-button>
            <el-button type="danger" @click="deleteQuestion(scope.$index)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
  
      <!-- 添加新试题按钮 -->
      <el-button type="primary" @click="openQuestionDialog">添加新试题</el-button>
  
      <!-- 新建/编辑试题弹窗 -->
      <el-dialog :title="dialogTitle" v-model="questionDialogVisible">
        <el-input v-model="currentQuestion.content" placeholder="请输入试题内容"></el-input>
        <br><br>
        <el-input v-model="currentQuestion.options.A" placeholder="选项 A"></el-input>
        <br><br>
        <el-input v-model="currentQuestion.options.B" placeholder="选项 B"></el-input>
        <br><br>
        <el-input v-model="currentQuestion.options.C" placeholder="选项 C"></el-input>
        <br><br>
        <el-input v-model="currentQuestion.options.D" placeholder="选项 D"></el-input>
        <br><br>
        <el-select v-model="currentQuestion.answer" placeholder="选择正确答案">
          <el-option label="A" value="A"></el-option>
          <el-option label="B" value="B"></el-option>
          <el-option label="C" value="C"></el-option>
          <el-option label="D" value="D"></el-option>
        </el-select>
        <br><br>
        <el-input v-model.number="currentQuestion.percentage" placeholder="请输入占比 (%)" type="number" :maxlength="3" @input="checkPercentageLimit"></el-input>
        <p v-if="percentageExceeded" style="color: red">所有题目总占比不能超过100%</p>
  
        <span slot="footer" class="dialog-footer">
          <el-button @click="questionDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="saveQuestion" :disabled="percentageExceeded">保存</el-button>
        </span>
      </el-dialog>
  
      <!-- 提交试卷按钮 -->
      <el-button type="success" @click="submitExam">提交试卷</el-button>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from "vue";
  import axios from "axios";
  import { courseList } from "@/api/user";
import request from "@/utils/request";
  
  const props = defineProps(['examId']);
  const examId = props.examId;
  
  // 试卷基本信息
  const examTitle = ref("");
  const examDescription = ref("");
  const selectedCourseId = ref(""); // 当前选择的课程ID
  const startTime = ref(null); // 考试开始时间
  const endTime = ref(null); // 考试结束时间
  
  // 试题列表
  const questions = ref([
    { question_id: 1, content: "What is the capital of France?", options: { A: "Paris", B: "London", C: "Berlin", D: "Rome" }, answer: "A", percentage: 25 },
    { question_id: 2, content: "What is 2 + 2?", options: { A: "3", B: "4", C: "5", D: "6" }, answer: "B", percentage: 30 },
  ]);
  
  // 课程列表
  const courses = ref([]);
  
  // 获取课程列表
  const getCourses = async () => {
    let result = await courseList();
    courses.value = result;
  };
  const get = async () => {
    const response = await request.get(`/exams/${examId}/questions`);
    console.log(response);
    questions.value=response;
    console.log(questions.value)
    return response; // 这里返回的是服务器的响应
    };
    const fetchQuestions = async() => {
    console.log(examId)
  
    questions.value=await get();
    console.log(questions.value)
    }
  onMounted(() => {

    getCourses();
    if (examId){
        fetchQuestions();
    }
  });
  
  // 弹窗控制
  const questionDialogVisible = ref(false);
  const dialogTitle = ref("添加新试题");
  const currentQuestion = ref({
    question_id: null,
    content: "",
    options: { A: "", B: "", C: "", D: "" },
    answer: "",
    percentage: 0,
  });
  
  const percentageExceeded = ref(false); // 用于显示总占比是否超过100%
  
  // 检查当前试题的占比输入
  const checkPercentageLimit = () => {
    const totalPercentage = questions.value.reduce((total, question) => total + question.percentage, 0);
    const newTotal = totalPercentage - (currentQuestion.value.percentage || 0) + currentQuestion.value.percentage;
    percentageExceeded.value = newTotal > 100;
  };
  
  // 添加或编辑试题
  const openQuestionDialog = () => {
    dialogTitle.value = "添加新试题";
    currentQuestion.value = { question_id: null, content: "", options: { A: "", B: "", C: "", D: "" }, answer: "", percentage: 0 };
    percentageExceeded.value = false;
    questionDialogVisible.value = true;
  };
  
  const editQuestion = (question) => {
    console.log(question)
    dialogTitle.value = "编辑试题";
    currentQuestion.value = JSON.parse(JSON.stringify(question));
    percentageExceeded.value = false;
    questionDialogVisible.value = true;
  };
  
  const saveQuestion = () => {
    const totalPercentage = questions.value.reduce((total, question) => total + question.percentage, 0);
  
    // 如果是编辑模式
    if (currentQuestion.value.question_id) {
      const index = questions.value.findIndex((q) => q.question_id === currentQuestion.value.question_id);
      const newTotal = totalPercentage - questions.value[index].percentage + currentQuestion.value.percentage;
      if (newTotal > 100) {
        percentageExceeded.value = true;
        return;
      }
      if (index !== -1) {
        questions.value[index] = { ...currentQuestion.value };
      }
    } else {
      // 新增模式
      const newTotal = totalPercentage + currentQuestion.value.percentage;
      if (newTotal > 100) {
        percentageExceeded.value = true;
        return;
      }
      currentQuestion.value.question_id = Date.now();
      questions.value.push({ ...currentQuestion.value });
    }
    questionDialogVisible.value = false;
  };
  
  const deleteQuestion = (index) => {
    questions.value.splice(index, 1);
    percentageExceeded.value = false;
  };
  
 // 提交试卷
const submitExam = async () => {
  try {
    const formatDateTime = (date) => {
  if (!date) return null;
  const offsetDate = new Date(date.getTime() - date.getTimezoneOffset() * 60000);
  return offsetDate.toISOString().slice(0, 19); // 去掉毫秒部分
};



    const examData = {
      title: examTitle.value,
      description: examDescription.value,
      courseId: selectedCourseId.value, // 传递选择的课程ID
      startTime: formatDateTime(startTime.value), // 格式化开始时间
      endTime: formatDateTime(endTime.value), // 格式化结束时间
      questions: questions.value,
    };
    console.log(examData);
    const response = await request.post("/exams/create", examData);
    console.log(response)
  } catch (error) {
    console.error("提交试卷时发生错误：", error);
  }
};
  </script>
  
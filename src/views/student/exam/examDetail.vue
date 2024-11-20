<template>
    <div class="exam-container">
      <h2 class="exam-title">考试进行中 - 当前考试ID是: {{ examId }}</h2>
    
      <!-- 当前题目卡片 -->
      <el-card v-if="question" class="question-card">
        <div class="question-title">{{ question.content }}</div>
    
        <!-- 答案选择 -->
        <el-radio-group v-model="answers[currentIndex].answer">
          <el-radio-button
            v-for="(option, key) in question.options"
            :key="key"
            :label="key"
          >
            {{ key }}: {{ option }}
          </el-radio-button>
        </el-radio-group>
    
        <!-- 操作按钮 -->
        <div class="actions">
          <el-button v-if="currentIndex < questions.length - 1" @click="nextQuestion" type="primary">下一题</el-button>
          <el-button v-else @click="submitAllAnswers" type="success">提交考试</el-button>
        </div>
      </el-card>
    
      <!-- 考试完成页面 -->
      <el-result v-else title="考试完成" icon="success">
      </el-result>
    
      
    
      
    
      <!-- 题目导航 -->
      <div class="navigation">
        <el-button
          v-for="(answer, index) in answers"
          :key="index"
          :type="answer.answer !== null ? 'success' : 'info'"
          @click="goToQuestion(index)"
        >
          题目 {{ index + 1 }}
        </el-button>
      </div>
      <br><br><br>
      <el-button @click="goBack" type="primary">返回考试列表</el-button>
    </div>
  </template>
  
  
  <script setup>
  import { ref, onMounted } from 'vue'
  import { ElMessage } from 'element-plus'
  import { useRouter } from 'vue-router'
  import request from '@/utils/request';
  const props = defineProps(['examId'])
  const examId = props.examId
  const router = useRouter()
  
  // 定义响应式变量
  const questions = ref([]) // 存储所有题目
  const currentIndex = ref(0) // 当前题目索引
  const answers = ref([]) // 存储用户的答案（{ question_id, answer } 格式）
  const question = ref(null) // 当前题目
  let score=100
  const get = async () => {
    const response = await request.get(`/exams/${examId}/questions`);
    console.log(response);
    questions.value=response;
    console.log(questions.value)
    return response; // 这里返回的是服务器的响应
    };

  // 静态数据模拟
  const fetchQuestions = async() => {
    console.log(examId)
    // questions.value = [
    //   {
    //     question_id: 1,
    //     content: "What is the capital of France?",
    //     options: {
    //       A: "Paris",
    //       B: "London",
    //       C: "Berlin",
    //       D: "Rome",
    //     },
    //   },
    //   {
    //     question_id: 100,
    //     content: "What is 2 + 2100?",
    //     options: {
    //       A: "3",
    //       B: "4",
    //       C: "5",
    //       D: "6",
    //     },
    //   },
    //   {
    //     question_id: 3,
    //     content: "Which planet is known as the Red Planet?",
    //     options: {
    //       A: "Earth",
    //       B: "Mars",
    //       C: "Jupiter",
    //       D: "Saturn",
    //     },
    //   },
    // ]
    questions.value=await get();
    console.log(questions.value)
    // 初始化答案数组，并加载第一道题
    if (questions.value.length > 0) {
      answers.value = questions.value.map(q => ({ question_id: q.question_id, answer: null }))
      loadQuestion()
    } else {
      ElMessage.error('没有加载到任何试题')
    }
  }
  
  // 加载当前题目
  const loadQuestion = () => {
    question.value = questions.value[currentIndex.value]
  }
  
  // 跳转到指定题目
  const goToQuestion = (index) => {
    if (index < 0 || index >= questions.value.length) return
    currentIndex.value = index
    loadQuestion()
  }
  
  // 切换到下一题
  const nextQuestion = () => {
    if (answers.value[currentIndex.value].answer === null) {
      ElMessage.warning('请选择一个答案')
      return
    }
    currentIndex.value++
    loadQuestion()
  }
  
  // 提交所有答案
  const submitAllAnswers = async() => {
    if (answers.value.some(a => a.answer === null)) {
      ElMessage.warning('所有题目都需要作答')
      return
    }
    const response = await request.post(`/exams/${examId}/submit`,answers.value);
     score=response;
    console.log('提交的答案:', answers.value)
    console.log(response);
    ElMessage.success('考试已完成，答案已提交')
    question.value = null // 表示考试结束
    console.log(score)
    router.push({ name: 'ExamEnd', params: { score } })
  }
  
  // 返回考试列表页面
  const goBack = () => {
    router.push('/student/exam/list')
  }
  
  onMounted(fetchQuestions)
  </script>
  
  <style scoped>
/* 外部容器：整体居中布局 */
.exam-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  padding: 20px;
  background-color: #f5f5f5;
}

/* 标题样式 */
.exam-title {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 20px;
  color: #333;
  text-align: center;
}

/* 卡片样式 */
.question-card {
  width: 80%;
  max-width: 800px;
  padding: 30px;
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  align-items: center;
}

/* 题目标题样式 */
.question-title {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 20px;
  text-align: center;
}

/* 答案选择区域 */
.el-radio-group {
  width: 100%; /* 占据卡片全宽 */
  display: flex;
  flex-direction: column;
  gap: 15px; /* 选项间距 */
}

/* 单个选项按钮 */
.el-radio-button {
  text-align: left; /* 内容靠左 */
  white-space: normal; /* 允许内容换行 */
  word-wrap: break-word; /* 长单词自动换行 */
  word-break: break-word; /* 避免长字符串溢出 */
  width: 100%; /* 选项按钮占满宽度 */
  border-radius: 0; /* 移除圆角 */
  padding: 10px 15px; /* 按钮内边距 */
}

/* 解决左边“封口”问题 */
.el-radio-button:first-child {
  border-top-left-radius: 5px; /* 左上圆角 */
  border-bottom-left-radius: 5px; /* 左下圆角 */
}

.el-radio-button:last-child {
  border-top-right-radius: 5px; /* 右上圆角 */
  border-bottom-right-radius: 5px; /* 右下圆角 */
}

/* 操作按钮区域 */
.actions {
  margin-top: 20px;
  display: flex;
  justify-content: center;
  gap: 10px;
}

/* 题目导航 */
.navigation {
  margin-top: 20px;
  display: flex;
  gap: 10px;
  flex-wrap: wrap;
  justify-content: center;
}
</style>

<template>
    <div>
      <!-- 试卷列表展示 -->
      <el-table :data="examList" style="width: 100%">
        <el-table-column prop="name" label="试卷名称"></el-table-column>
        <el-table-column prop="description" label="描述"></el-table-column>
        <el-table-column prop="start_time" label="开始时间"></el-table-column>
        <el-table-column label="操作">
          <template #default="scope">
            <el-button type="primary" @click="viewExam(scope.row)">查看</el-button>
            <el-button type="warning" @click="editExam(scope.row)">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
  
      <!-- 新建试卷按钮 -->
      <el-button type="success" @click="createExam">新建试卷</el-button>
    </div>
  </template>
  
  <script setup>
  import { ref,onMounted } from "vue";
  import { useRouter } from "vue-router";
  import { ElMessage } from 'element-plus'
  import request from '@/utils/request';
  // 示例试卷列表数据
  const examList = ref([
    { examId: 1, name: "General Knowledge Test", description: "A test on general knowledge.", start_time: "2024-12-01T09:00:00" },
    { examId: 2, name: "Math Test", description: "Basic math quiz.", start_time: "2024-12-02T10:00:00" }
  ]);
  
  const router = useRouter();
  const fetchExams = async () => {
    try {
      const response = await request.get('/exams')
      console.log(response)
      examList.value = response
    } catch (error) {
      ElMessage.error('获取待考试列表失败')
    }
  }
  // 查看试卷
  const viewExam = (exam) => {
    console.log(exam.examId)
    router.push({ name: "teacherEditExam", params: { examId: exam.examId, viewOnly: true } });
  };
  
  // 编辑试卷
  const editExam = (exam) => {
    router.push({ name: "teacherEditExam", params: { examId: exam.examId, viewOnly: false } });
  };
  
  // 新建试卷
  const createExam = () => {
    router.push({ name: "teacherEditExam", params: { examId: null, viewOnly: false } });
  };

  onMounted(() => {
    fetchExams()
  })
  </script>
  
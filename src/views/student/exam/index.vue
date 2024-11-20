<template>
    <div>
      <el-table :data="examList" style="width: 100%">
        <el-table-column prop="name" label="考试名称" width="180"></el-table-column>
        <!-- <el-table-column prop="course_name" label="课程名称" width="180"></el-table-column> -->
        <el-table-column prop="description" label="描述"></el-table-column>
        <el-table-column prop="startTime" label="开始时间" width="180">
          <template #default="{ row }">
            <span>{{ formatDate(row.startTime) }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="endTime" label="结束时间" width="180">
          <template #default="{ row }">
            <span>{{ formatDate(row.endTime) }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="120">
            
          <template #default="{ row }">
            <el-button @click="startExam(row.examId)" type="primary" size="small">进入考试</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue'
  import { useRouter } from 'vue-router'
  import { ElMessage } from 'element-plus'
  import axios from 'axios'
import request from '@/utils/request';
  
  const router = useRouter()
  const examList = ref([
    // {
    //   exam_id: 1,
    //   name: "General Knowledge Test",
    //   description: "A test on general knowledge topics.",
    //   start_time: "2024-12-01T09:00:00",
    //   course_name: "General Knowledge"
    // },
    // {
    //   exam_id: 2,
    //   name: "Math Test",
    //   description: "A basic math quiz.",
    //   start_time: "2024-12-02T10:00:00",
    //   course_name: "Mathematics"
    // },
    // {
    //   exam_id: 3,
    //   name: "Literature Test",
    //   description: "A test on literature knowledge.",
    //   start_time: "2024-12-03T13:00:00",
    //   course_name: "Literature"
    // },
    // {
    //   exam_id: 4,
    //   name: "Science Quiz",
    //   description: "A general science quiz covering various topics.",
    //   start_time: "2024-12-04T15:00:00",
    //   course_name: "Science"
    // }
  ])
  
  // 格式化日期
  const formatDate = (dateStr) => {
    const date = new Date(dateStr)
    return date.toLocaleString()
  }
  
  // 获取待考试列表
  const fetchExams = async () => {
    try {
      const response = await request.get('/exams')
      console.log(response)
      examList.value = response
    } catch (error) {
      ElMessage.error('获取待考试列表失败')
    }
  }
  
  // 跳转到考试页面
  const startExam = (examId) => {
    router.push({ name: 'ExamPage', params: { examId } })
  }
  
  onMounted(() => {
    fetchExams()
  })
  </script>
  
  <style scoped>
  /* 可选的样式 */
  </style>
  
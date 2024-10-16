<template>
    <el-row :gutter="16">
      <el-col :span="8" v-for="i in courses" :key="i.id">
        <router-link :to="{ name: 'CourseDetail', params: { id: i.id } }" style="text-decoration: none; color: inherit;">
          <el-card style="max-width: 480px; margin-bottom: 16px;" shadow="hover">
            <template #header>
              <div class="card-header">
                <span>{{ i.name }}</span>
                <small>{{ i.time }}</small>
              </div>
            </template>
            <img :src="i.img" style="width: 100%; height: 200px; object-fit: cover;" />
            <div class="card-content">
              <p>{{ i.description }}</p>
            </div>
          </el-card>
        </router-link>
      </el-col>
    </el-row>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  
  const courses = ref([
    {
      id: 1,
      name: "机器学习",
      time: "2024-10-16 10:00 - 12:00",
      img: "https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png",
      description: "介绍机器学习的基本概念和算法。"
    },
    {
      id: 2,
      name: "机器学习2",
      time: "2024-10-17 14:00 - 16:00",
      img: "https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png",
      description: "深入探讨监督学习和无监督学习。"
    },
    {
      id: 3,
      name: "机器学习3",
      time: "2024-10-18 10:00 - 12:00",
      img: "https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png",
      description: "讲解深度学习和神经网络。"
    },
    {
      id: 4,
      name: "机器学习4",
      time: "2024-10-19 14:00 - 16:00",
      img: "https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png",
      description: "实践项目和案例分析。"
    },
    {
      id: 5,
      name: "机器学习5",
      time: "2024-10-20 10:00 - 12:00",
      img: "https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png",
      description: "综合复习和考试准备。"
    }
  ]);
  </script>
  
  <style scoped>
  .el-row {
    margin-bottom: 20px;
  }
  .el-row:last-child {
    margin-bottom: 0;
  }
  .card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  .card-content {
    padding: 10px;
  }
  </style>
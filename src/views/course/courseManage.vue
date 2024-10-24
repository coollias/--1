<template>
    <el-container>
      <el-header>
        <h2>课程管理系统</h2>
      </el-header>
  
      <el-main>
        <el-button type="primary" @click="openAddCourseDialog">添加课程</el-button>
  
        <el-table :data="courses" style="width: 100%">
          <el-table-column prop="id" label="课程ID" width="100"></el-table-column>
          <el-table-column prop="name" label="课程名称"></el-table-column>
          <el-table-column prop="teacher" label="教师"></el-table-column>
          <el-table-column prop="credits" label="学分"></el-table-column>
          <el-table-column label="操作">
            <template #default="scope">
              <el-button size="small" @click="editCourse(scope.row)">编辑</el-button>
              <el-button size="small" type="danger" @click="deleteCourse(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
  
        <!-- 添加课程的弹窗 -->
        <el-dialog title="添加课程" :visible.sync="addCourseDialogVisible">
          <el-form :model="newCourse">
            <el-form-item label="课程名称">
              <el-input v-model="newCourse.name"></el-input>
            </el-form-item>
            <el-form-item label="教师">
              <el-input v-model="newCourse.teacher"></el-input>
            </el-form-item>
            <el-form-item label="学分">
              <el-input v-model="newCourse.credits"></el-input>
            </el-form-item>
          </el-form>
  
          <template #footer>
            <el-button @click="addCourseDialogVisible.value = false">取消</el-button>
            <el-button type="primary" @click="addCourse">添加</el-button>
          </template>
        </el-dialog>
      </el-main>
    </el-container>
  </template>
  
  <script>
  import { ref, reactive } from 'vue';
  
  export default {
    setup() {
      // 响应式数据
      const courses = ref([
        { id: 1, name: '数学', teacher: '张老师', credits: 3 },
        { id: 2, name: '物理', teacher: '李老师', credits: 4 },
        // 其他课程数据
      ]);
  
      const addCourseDialogVisible = ref(false);  // 控制弹窗可见性
  
      const newCourse = reactive({
        name: '',
        teacher: '',
        credits: ''
      });
  
      // 打开添加课程的对话框
      const openAddCourseDialog = () => {
        console.log(addCourseDialogVisible.value);
        newCourse.name = '';
        newCourse.teacher = '';
        newCourse.credits = '';
        addCourseDialogVisible.value = true;
        console.log(addCourseDialogVisible.value);
      };
  
      // 添加课程
      const addCourse = () => {
        const newId = courses.value.length + 1;
        courses.value.push({ id: newId, ...newCourse });
        addCourseDialogVisible.value = false;
      };
  
      // 编辑课程
      const editCourse = (course) => {
        // 处理编辑逻辑
        console.log('编辑课程:', course);
      };
  
      // 删除课程
      const deleteCourse = (course) => {
        courses.value = courses.value.filter(c => c.id !== course.id);
      };
  
      return {
        courses,
        addCourseDialogVisible,
        newCourse,
        openAddCourseDialog,
        addCourse,
        editCourse,
        deleteCourse
      };
    }
  };
  </script>
  
  <style>
  h2 {
    margin: 0;
    padding: 16px;
  }
  </style>
  
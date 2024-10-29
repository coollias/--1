
<template>
  <el-container> 
    <el-header>
      <h2>课程管理系统</h2>
    </el-header>

    <el-main>
      <el-button type="primary" @click="AddCourseDialog = true">添加课程</el-button>

      <el-table :data="filterTableData" style="width: 100%">
        <el-table-column label="课程名" prop="name" />
        <el-table-column label="教师" prop="teacher" />
        <el-table-column label="学分" prop="credit" />
        <el-table-column align="right">
          <template #header>
            <el-input v-model="search" size="small" placeholder="搜索课程" />
          </template>
          <template #default="scope">
            <el-button size="small" type="primary" plain @click="handleEdit(scope.$index, scope.row)">
              Edit
            </el-button>
            <el-button size="small" type="danger" plain @click="handleDelete(scope.$index, scope.row)">
              Delete
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <el-dialog v-model="AddCourseDialog" title="添加课程" width="500">
        <el-form :model="form">
          <el-form-item label="课程名称" :label-width="formLabelWidth">
            <el-input v-model="form.name" autocomplete="off" />
          </el-form-item>
          <el-form-item label="教师名称" :label-width="formLabelWidth">
            <el-input v-model="form.teacher" autocomplete="off" />
          </el-form-item>
          <el-form-item label="学分" :label-width="formLabelWidth">
            <el-select v-model="form.credit" placeholder="请选择学分">
              <el-option label="2" value="2" />
              <el-option label="3" value="3" />
              <el-option label="4" value="4" />
            </el-select>
          </el-form-item>
        </el-form>
        <template #footer>
          <div class="dialog-footer">
            <el-button @click="AddCourseDialog = false">Cancel</el-button>
            <el-button type="primary" @click="AddCourse()">
              Confirm
            </el-button>
          </div>
        </template>
      </el-dialog>
    </el-main>
    
    
  </el-container>
</template>
  
  <script lang="ts" setup>
  import { reactive, computed, ref } from 'vue'

  const AddCourseDialog = ref(false)
  const formLabelWidth = '140px'

  const form = reactive({
    name: '',
    teacher: '',
    credit: '',
  })

  const AddCourse = () => {
    // 将表单数据添加到课程列表中
    tableData.push({
      name: form.name,
      teacher: form.teacher,
      credit: form.credit,
    })

    console.log(form.name)
    // 清空表单并关闭对话框
    form.name = ''
    form.teacher = ''
    form.credit = ''
    AddCourseDialog.value = false
  }
  
  interface course {
    name: string
    teacher: string
    credit: string
  }
  
  const search = ref('')
  const filterTableData = computed(() =>
    tableData.filter(
      (data) =>
        !search.value ||
        data.name.toLowerCase().includes(search.value.toLowerCase())
    )
  )
  const handleEdit = (index: number, row: course) => {
    console.log(index, row)
  }
  const handleDelete = (index: number, row: course) => {
    console.log(index, row)
  }
  
  const tableData = reactive<course[]> ([
    {
      name: '微积分',
      teacher: '冯国臣',
      credit: '4',
    },
    {
      name: '线性代数',
      teacher: '冯国臣',
      credit: '3',
    },
    {
      name: '项目管理与运维',
      teacher: '邸晓飞',
      credit: '3',
    },
    {
      name: '实训',
      teacher: '刘海明',
      credit: '2',
    },
  ])
  </script>
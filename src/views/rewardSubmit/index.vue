<template>
  <div>
    <!-- 文件上传输入框 -->
    <el-row :gutter="20">
    <el-col :span="6"><el-upload
      class="upload-demo"
      action=""
      :on-change="handleFileUpload"
      :auto-upload="false"
      :show-file-list="false"
    >
      <el-button slot="trigger" type="primary">选择文件</el-button>
 
    </el-upload></el-col>
    <el-col :span="6">    <el-button type="success" @click="clearFileSelection">撤销选择</el-button></el-col>

    <el-col :span="6">    <el-button type="success" @click="add">确定导入</el-button></el-col>

  </el-row>
    


    <!-- 自定义文件列表展示 -->
    <div class="file-list" v-if="files.length > 0">
      <p>已选择文件：</p>
      <ul>
        <li v-for="(file, index) in files" :key="index">{{ file.name }}</li>
      </ul>
    </div>
    <!-- 表格展示 -->
    <el-table :data="tableData" style="width: 100%; margin-top: 20px;">
      <el-table-column prop="aac003" label="学号" width="150"></el-table-column>
      <el-table-column prop="bac313" label="姓名" width="200"></el-table-column>
      <el-table-column prop="bac314" label="性别" width="200"></el-table-column>
      <el-table-column prop="bac502" label="身份证号" width="300"></el-table-column>
    </el-table>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { ElMessage } from 'element-plus'; // 假设使用的是 Element Plus 消息提示组件
import * as XLSX from 'xlsx'; // 导入 xlsx 库
import {studentInfoAdd} from '@/api/user/index.ts'

const files = ref([]);
const tableData = ref([]); // 读取所有的数据数组
const fileInput = ref(null); // 用于引用文件输入框

const handleFileUpload = (file, fileList) => {
  const uploadedFile = file.raw;
  files.value = [uploadedFile];

  const imgType = uploadedFile.type === "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
  if (!imgType) {
    ElMessage.error("请选择Excel文件");
    return false;
  }

  const isLt10M = uploadedFile.size / 1024 / 1024 < 10;
  if (!isLt10M) {
    ElMessage.error("文件大小不能大于10M!");
    return false;
  }

  tableData.value = []; // 清空之前的数据

  const reader = new FileReader();
  reader.onload = (e) => {
    const data = e.target.result;
    let workbook;
    if (typeof data === 'string') { // 对于旧版xlsx，可能直接是二进制字符串
      workbook = XLSX.read(data, { type: 'binary' });
    } else { // 对于新版xlsx，通常为ArrayBuffer
      workbook = XLSX.read(new Uint8Array(data), { type: 'array' });
    }

    const worksheet = workbook.Sheets[workbook.SheetNames[0]];
    const requiredColumnsOrder = ['学号', '姓名', '性别', '身份证号'];
    const headerRow = XLSX.utils.sheet_to_json(worksheet, { header: 1 })[0];

    if (requiredColumnsOrder.some((col, index) => headerRow[index] !== col)) {
      ElMessage.error('模板文件的结构不正确，请检查列名或列顺序！');
      return;
    }

    let excelData = XLSX.utils.sheet_to_json(worksheet);

    const fieldMap = {
      '学号': 'aac003',
      '姓名': 'bac313',
      '性别': 'bac314',
      '身份证号': 'bac502',
    };

    excelData = excelData.map(row => {
      return Object.keys(row).reduce((newRow, key) => {
        if (fieldMap[key]) {
          newRow[fieldMap[key]] = row[key];
        } else {
          newRow[key] = row[key]; // 如果没有对应英文名称，保留原字段名
        }
        return newRow;
      }, {});
    });

    tableData.value = excelData;
    console.log(tableData.value);
  };

  reader.readAsArrayBuffer(uploadedFile); // 读取为ArrayBuffer，适用于大多数情况
};

const clearFileSelection = () => {
  if (fileInput.value) {
    fileInput.value.value = ''; // 清空文件输入框的值
  }
  tableData.value = []; // 清空表格数据
  files.value = []; // 清空文件列表
};

const add=()=>{
  studentInfoAdd(tableData.value);
}
</script>

<style >
/* 添加一些样式以美化表格 */
.el-table {
  margin-top: 20px;
}

.upload-demo {
  display: flex;
  gap: 10px;
}


.el-row {
  margin-bottom: 20px;
}
.el-row:last-child {
  margin-bottom: 0;
}
.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
</style>
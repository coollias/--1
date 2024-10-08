<template>
     <el-card class="box-card">
    <!-- 表格组件 -->
    <el-table :data="tableData" :style="{ width: '100%' }" >
      <el-table-column prop="Sname" label="学生姓名" width="180" />
        <el-table-column prop="num" label="序号" width="180" />
        <el-table-column prop="Rname" label="奖项名称" width="180" />
        <el-table-column prop="rank" label="位次" />
        <el-table-column fixed="right" label="Operations" width="200">
      <template #default>
        <el-button link type="primary" size="small" @click="handleClick"
          >审核通过</el-button
        >
        <el-button link type="primary" size="small">审核不通过</el-button>
      </template>
    </el-table-column>
    </el-table>
  </el-card>
</template>
  
<script setup lang='ts'>
import { ref } from 'vue';
import { awardInfo} from '@/api/user';
const tableData=ref([
    
    ])

const get=async()=>{
  let result=await awardInfo();
  console.log(result.checkUser.data);
  tableData.value=result.checkUser.data;
}

get();
</script>
  
<style>
  
</style>
<template>
    <div>
        <el-result  title="考试完成" icon="success">
            <template #extra>
                <p>您的得分是：<strong>{{ score }}</strong> 分</p>
            </template>
      </el-result>
      
      <el-button @click="goBack" type="primary">返回考试列表</el-button>

    </div>
</template>

<script setup>
 import { useRouter } from 'vue-router'
 const props = defineProps(['score'])
  const score = props.score
  console.log(score)
  const router = useRouter()
const goBack = () => {
    router.push('/student/exam/list')
  }
</script>

<style>
p {
    font-size: 16px;
    color: #606266;
    margin: 0;
}
strong {
    font-size: 18px;
    color: #409EFF;
}</style>
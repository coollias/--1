import { onMounted } from 'vue';
<template>
    <div><p>欢迎来到主页</p></div>
</template>
  
<script setup lang='ts'>
  import {onMounted} from 'vue'
  import useUserStore from '@/store/modules/user';
  //首页挂在完毕，请求用户信息
  let UserStore=useUserStore();
  onMounted(()=>{
    UserStore.userInfo();
  })

 
</script>
  
<style>
  
</style>
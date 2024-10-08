
<template>
    <el-button size="small"  icon="Refresh" circle="true"></el-button>
      <el-button size="small"  icon="Setting" circle="true"></el-button>
      <img :src="userStore.avatar" style="width:25px;height:25px;margin:0px 10px">
      <el-dropdown>
        <span class="el-dropdown-link">
          {{ userStore.username }}
          <el-icon class="el-icon--right">
            <arrow-down />
          </el-icon>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item>退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
</template>
  
<script setup lang='ts'>
import useUserStore from '@/store/modules/user.ts';

  let userStore=useUserStore();
</script>
  
<style>
  
</style>
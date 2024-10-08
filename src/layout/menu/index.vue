import { useRouter } from 'vue-router';
<template>
      <template v-for="(item, index) in menuList" :key="item.path">
        <!-- 只有一级 -->
        <template v-if="!item.children">
          <el-menu-item v-if="!item.meta.hidden" :index="item.path" @click="goRoute">
            <el-icon>
            <component :is="item.meta.icon"></component>
          </el-icon>
        <template #title>
         
          <span>{{ item.meta.title }}</span>
        </template>
      </el-menu-item>
        </template>
      <!-- 有一个二级 -->
      <template v-if="item.children&&item.children.length==1">
        <el-menu-item v-if="!item.children[0].meta.hidden" :index="item.children[0].path" @click="goRoute">
          <el-icon>
            <component :is="item.meta.icon"></component>
          </el-icon>
      <template #title>
        <span>{{ item.children[0].meta.title }}</span>
      </template>
      </el-menu-item>
      </template>
      
      <!-- 大于一个子路由 -->
      <el-sub-menu v-if="item.children&&item.children.length>1" :index="item.path">
        <template #title>
          <el-icon>
            <component :is="item.meta.icon"></component>
          </el-icon>
        <span>{{ item.meta.title }}</span>
        </template>
        <!-- 组件递归 -->
        <Menu :menuList="item.children"></Menu>
      </el-sub-menu>
      </template>
</template>
  
<script setup lang='ts'>
import {RouteLocationRaw, useRouter} from 'vue-router'

let $router=useRouter();
  //获取父组件传递的全部路由
  defineProps(['menuList'])

  const goRoute=(vc: { index: RouteLocationRaw; })=>{
    $router.push(vc.index);
  }
</script>

<script lang="ts">
export default{
  name:'Menu',
}
</script>
  
<style>
  
</style>
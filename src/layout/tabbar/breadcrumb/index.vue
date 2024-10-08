<template>
     <el-icon style="margin-right:10px" @click="changeIcon">
        <component :is="LayOutSettingStore.fold?'Fold':'Expand'"></component>
      </el-icon>
      <el-breadcrumb separator-icon="ArrowRight">
        <!-- 面包屑动态展示 -->
        <el-breadcrumb-item v-for="(item,index) in $route.matched" :key="index" v-show="item.meta.title" :to="item.path">
          <el-icon >
            <component :is="item.meta.icon"></component>
          </el-icon>
        <span style="margin:0px 5px;">{{ item.meta.title }}</span>
        </el-breadcrumb-item>
      </el-breadcrumb>
</template>
  
<script setup lang='ts'>
import useLayOutSettingStore from '@/store/modules/setting.ts'
import { useRoute } from 'vue-router';

let $route=useRoute();
let LayOutSettingStore =useLayOutSettingStore();
  const changeIcon=()=>{
    LayOutSettingStore.fold = !LayOutSettingStore.fold;
  }

  const handle=()=>{
    console.log($route.matched);
  }

</script>

<script lang="ts">
export default{
  name:"Breadcrunmb"
}
</script>
  
<style>
  
</style>
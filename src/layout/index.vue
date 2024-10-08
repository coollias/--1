<template>
    <div class="layout_container">
      <!-- 左侧菜单 -->
      <div class="layout_slidder" :class="{fold:LayOutSettingStore.fold?true:false}">
        <logo></logo>
        <!-- 展示菜单 -->
        <el-scrollbar class="scrollbar">
        <!-- 菜单 -->
        <el-menu :collapse="LayOutSettingStore.fold?true:false" background-color="#005bac" text-color="white" :default-active="$route.path">

          <Menu :menuList="userStore.menuRoutes"></Menu>
        </el-menu>
        </el-scrollbar>
      </div>
      <!-- 顶部导航 -->
      <div class="layout_tabbar" :class="{fold:LayOutSettingStore.fold?true:false}">
        <Tabbar></Tabbar>
      </div>
      <!-- 内容展示 -->
      <div class="layout_main" :class="{fold:LayOutSettingStore.fold?true:false}">
        <router-view></router-view>
      </div>
    </div>
</template>
  
<script setup lang='ts'>
import logo from './logo/index.vue'
import Menu from './menu/index.vue'
import Tabbar from './tabbar/index.vue'
import useUserStore from '@/store/modules/user';
import { useRoute } from 'vue-router';
import useLayOutSettingStore from '@/store/modules/setting';
let LayOutSettingStore=useLayOutSettingStore();

let $route=useRoute();
let userStore=useUserStore();
</script>
  
<script lang="ts">
export default{
  name:"Layout"
}
</script>
<style scoped lang="scss">
  $slidder_width:240px;
  $tabbar_height:50px;
  .layout_container{
    width:100%;
    height:100vh;


    .layout_slidder{
      width:$slidder_width;
      height:100vh;
      background:#005bac;
      transition: all 0.3s;
      .scrollbar{
        width:100%;
        height:calc(100vh - 50px);

        .el-menu{
          border-right: none;
        }
      }

      &.fold{
        width:50px;
        
      }
    }

    .layout_tabbar{
      position: fixed;
      width:calc(100% - $slidder_width);
      height:$tabbar_height;
      top:0;
      left:$slidder_width;
      transition: all 0.3s;

      &.fold{
        width:calc(100vw - 50px);
        left:50px;
      }
    }

    .layout_main{
      position:absolute;
      width:calc(100% - $slidder_width);
      height:calc(100vh - $tabbar_height);
      top:$tabbar_height;
      left:$slidder_width;
      padding:20px;
      overflow:auto;
      transition: all 0.3s;

      &.fold{
        width:calc(100vw - 50px);
        left:50px;
      }
    }
  }
</style>
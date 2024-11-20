<template>
    <div class="layout_container">
      <!-- 左侧菜单 -->
      <div class="layout_slidder" :class="{fold:LayOutSettingStore.fold?true:false}">
        <logo></logo>
        <!-- 展示菜单 -->
        <el-scrollbar class="scrollbar">
        <!-- 菜单 -->
        <el-menu :collapse="LayOutSettingStore.fold?true:false" background-color="#005bac" text-color="white" :default-active="$route.path">

          <Menu :menuList="getMenuRoutes"></Menu>
        </el-menu>
        </el-scrollbar>
      </div>
      <!-- 顶部导航 -->
      <div class="layout_tabbar" :class="{fold:LayOutSettingStore.fold?true:false}">
        <Tabbar></Tabbar>
      </div>
      <!-- 内容展示 -->
      <div class="layout_main" :class="{fold:LayOutSettingStore.fold?true:false}">
        <router-view ></router-view>
      </div>
    </div>
</template>
  
<script setup lang='ts'>
import logo from '../layout/logo/index.vue'
import Menu from './menu/index.vue'
import Tabbar from './tabbar/index.vue'
import useUserStore from '@/store/modules/user';
import { useRoute } from 'vue-router';
import useLayOutSettingStore from '@/store/modules/setting';
import { computed } from 'vue';
import {  constantRoute1 ,constantRoute2,constantRoute3} from "@/router/routes";
let LayOutSettingStore=useLayOutSettingStore();

let $route=useRoute();
let userStore=useUserStore();
console.log(userStore.identity)
// const getDynamicMenuList = computed(() => {
//   const identity = userStore.identity; // 获取用户身份
//   const menuListKey = `menuRoutes${identity}`; // 动态构建属性名
//   console.log(Reflect.get(userStore, menuListKey))
//   return Reflect.get(userStore, menuListKey) || []; // 使用 eval 动态返回
// });

// enum UserIdentity {
//   Student = "1",
//   Teacher = '2',
//   Admin = '3'
// }
const getMenuRoutes = computed(() => {
  const identity = userStore.identity; // 获取用户身份
  console.log(identity)
  if (identity == '1') { // 学生
    console.log("1")
    return constantRoute1;
  } 
  if (identity == '2') { // 教师
    console.log("2")
    return constantRoute2;
  } 
  if (identity == '3') { // 管理员
    console.log("3")
    return constantRoute3;
  } else {
    return []; // 默认返回空菜单
  }
});



// const getMenuRoutes = computed(() => {
//   const identity: string = userStore.identity; 

//   switch (identity) {
//     case UserIdentity.Student:
//       return userStore.menuRoutes1;
//     case UserIdentity.Teacher:
//       return userStore.menuRoutes2;
//     case UserIdentity.Admin:
//       return userStore.menuRoutes3;
//     default:
//       return [];
//   }
// });

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
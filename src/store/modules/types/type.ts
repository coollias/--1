import type { RouteRecordRaw } from "vue-router";
//定义小仓库数据
export interface UserState{
  token:string|null;
  //学生菜单路由
  menuRoutes1:RouteRecordRaw[];
  //老师菜单路由
  menuRoutes2:RouteRecordRaw[];
  //管理员菜单路由
  menuRoutes3:RouteRecordRaw[];

  
  username:string;
  avatar:string;
  identity:string|null;
}
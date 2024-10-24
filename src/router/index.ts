import { createRouter, createWebHashHistory } from "vue-router";
import { constantRoute } from "./routes";
import CourseDetail from '../views/course/courseDetail.vue'; // 假设你有一个 CourseDetail 组件

const routes = [
  ...constantRoute,
  {
    path: '/course/:id',
    name: 'CourseDetail',
    component: CourseDetail,
    meta: {
      title: '课程详情',
      hidden: false,
      icon: 'FolderAdd'
    }
  }
];


let router = createRouter({
  history: createWebHashHistory(),
  routes,
  scrollBehavior(){
    return{
      left:0,
      top:0
    }
  }
});

export default router;


// export default router;
import { createRouter, createWebHashHistory } from "vue-router";
import { constantRoute1,constantRoute2,constantRoute3} from "./routes";
import CourseDetail from '../views/course/courseDetail.vue'; // 假设你有一个 CourseDetail 组件

const routes = [
  ...constantRoute1,
   ...constantRoute2,
   ...constantRoute3
  // {
  //   path: '/course/:id',
  //   name: 'CourseDetail',
  //   component: CourseDetail,
  //   meta: {
  //     title: '课程详情',
  //     hidden: false,
  //     icon: 'FolderAdd'
  //   }
  // }
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
// 全局路由守卫，监听路由变化
// router.beforeEach((to, from, next) => {
//   console.log('Navigating from:', from.fullPath, 'to:', to.fullPath);

//   // 如果路由发生变化，强制刷新并跳转
//   if (to.path !== from.path) {
//     // 1. 刷新页面
//     // window.location.reload();
//     // 或者，你可以使用 router.push() 跳转到新的路由：
//     //  router.push(to.fullPath);
//     return; // 终止当前的路由跳转，让页面刷新或重新加载
//   }

//   next(); // 继续跳转
// });
export default router;
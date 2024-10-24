
//对外暴露常量路由
export const constantRoute= [
  
  {
    //登录
    path: "/login",
    component: () => import("@/views/login/index.vue"),
    name: "login", //命名路由
    meta:{
      title:'登录',
      hidden:true,//是否在菜单隐藏
      icon:'Aim'
    }
  },
  {
    //主页
    path: "/",
    component: () => import("@/layout/index.vue"),
    name: "layout",
    redirect: '/home',
    meta:{
      title:'',
      hidden:false,
      icon:'',
    },
    children:[
      {
        path:"/home",
        component:()=>import("@/views/home/index.vue"),
        name:'home',
        meta:{
          title:'首页',
          hidden:false,
          icon:'House',
        }
      },
    ]
  },
  {
    path:'/Student',
    component:()=>import("@/layout/index.vue"),
    name:'Student',
    redirect: '/Student/rSubmit',
    meta:{
      title:'学生内容',
      hidden:false,
      icon:'FolderAdd',
    },
    children:[
      {
        path:'/Student/rSubmit',
        component:()=>import('@/views/rewardSubmit/index.vue'),
        name:'rSubmit',
        meta:{
          title:'信息录入',
          hidden:false,
          icon:'FolderAdd'
        }
      },
      // {
      //   path:'/Student/cSubmit',
      //   component:()=>import('@/views/rewardSubmit/index.vue'),
      //   name:'cSubmit',
      //   meta:{
      //     title:'奖项确认',
      //     hidden:false,
      //     icon:'FolderAdd'
      //   }
      // },
    ]
  },
  {path:'/course',
    component:()=>import("@/layout/index.vue"),
    name:'course',
    redirect: '/course/list',
    meta:{
      title:'课程',
      hidden:false,
      icon:'FolderAdd',
    },
    children:[
      {
      path:'/course/list',
        component:()=>import('@/views/course/index.vue'),
        name:'courseList',
        meta:{
          title:'课程列表',
          hidden:false,
          icon:'FolderAdd'
        }
      },
      {
        path:'/course/manage',
          component:()=>import('@/views/course/courseManage.vue'),
          name:'courseManage',
          meta:{
            title:'课程管理',
            hidden:false,
            icon:'FolderAdd'
          }
        },
    ]

  },
  {
    path:'/acl',
    component:()=>import("@/layout/index.vue"),
    name:'Acl',
    redirect: '/acl/user',
    meta:{
      title:'权限管理',
      hidden:false,
      icon:'Select',
    },
    children:[
      {
        path:'/acl/user',
        component:()=>import('@/views/acl/user/index.vue'),
        name:'Acl',
        meta:{
          title:'用户管理',
          hidden:false,
          icon:'User',
        }
      },
      {
        path:'/acl/role',
        component:()=>import('@/views/acl/role/index.vue'),
        name:'Role',
        meta:{
          title:'角色管理',
          hidden:false,
          icon:'UserFilled',
        }
      },
      {
        path:'/acl/permission',
        component:()=>import('@/views/confirm/index.vue'),
        name:'Permission',
        meta:{
          title:'信息管理',
          hidden:false,
          icon:'Tickets',
        }
      },
    ]
  },
  {
    //404
    path: "/404",
    component: () => import("@/views/404/index.vue"),
    name: "404",
    meta:{
      title:'404',
      hidden:true,
      icon:"CircleCloseFilled"
    }
  },
  {
    path: "/:pathMatch(.*)*",
    redirect: "/404",
    name: "any",
    meta:{
      title:'任意路由',
      hidden:true,
      icon:"CircleCloseFilled"
    }
  },
  
]
//1,2,3都是能用的路由，只是导航栏选择其中一个
export const constantRoute1= [

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
   path: '/student/course/:id',
  name: 'studentCourseDetail',
  component:()=>import('@/views/student/course/CourseDetail.vue'),
  meta: {
  title: '课程详情',
  hidden: true,
  icon: 'FolderAdd'
  }
  },
  {
    path:'/student/exam/:examId',
      component:()=>import('@/views/student/exam/examDetail.vue'),
      name:'ExamPage',
      meta:{
        title:'考试进行',
        hidden:true,
        icon:'FolderAdd'
      },
      props: true,
    },
 
  {
    path:'/student',
    component:()=>import("@/layout/index.vue"),
    name:'studentlayout',
    redirect: '/student/home',
    meta:{
      title:'首页',
      hidden:false,
      icon:'FolderAdd',
    },
    children:[
      {
        path:'/student/home',
        component:()=>import('@/views/student/home/index.vue'),
        name:'studenthome',
        meta:{
          title:'学生首页',
          hidden:false,
          icon:'FolderAdd'
        }
      },

    ]
  },
  {
    path:'/scourse',
    component:()=>import("@/layout/index.vue"),
    name:'studentcourse',
    redirect: '/student/course/list',
    meta:{
      title:'课程',
      hidden:false,
      icon:'FolderAdd',
    },
    children:[
      {
      path:'/scourse/list',
        component:()=>import('@/views/student/course/index.vue'),
        name:'studentcourseList',
        meta:{
          title:'课程列表',
          hidden:false,
          icon:'FolderAdd'
        }
      },
      {
        path:'/scourse/manage',
          component:()=>import('@/views/student/course/courseManage.vue'),
          name:'studentcourseManage',
          meta:{
            title:'课程管理',
            hidden:false,
            icon:'FolderAdd'
          }
        },
        // {
        //   path: '/student/course/:id',
        //   name: 'studentCourseDetail',
        //   component:()=>import('@/views/student/course/CourseDetail.vue'),
        //   meta: {
        //     title: '课程详情',
        //     hidden: true,
        //     icon: 'FolderAdd'
        //   }
        // }
    ]

  },
  {path:'/student/notice',
    component:()=>import("@/layout/index.vue"),
    name:'studentnotice',
    redirect: '/student/notice/manage',
    meta:{
      title:'通知',
      hidden:false,
      icon:'FolderAdd',
    },
    children:[
      {
      path:'/student/notice/manage',
        component:()=>import('@/views/student/notice/index.vue'),
        name:'studentnoticeManage',
        meta:{
          title:'通知管理',
          hidden:false,
          icon:'FolderAdd'
        }
      }
      
    ]

  },
  {path:'/student/exam',
    component:()=>import("@/layout/index.vue"),
    name:'studentexam',
    redirect: '/student/exam/list',
    meta:{
      title:'考试',
      hidden:false,
      icon:'FolderAdd',
    },
    children:[
      {
      path:'/student/exam/list',
        component:()=>import('@/views/student/exam/index.vue'),
        name:'studentexamList',
        meta:{
          title:'待考列表',
          hidden:false,
          icon:'FolderAdd'
        }
      },
      {
        path:'/student/exam/create/:examId?',
          component:()=>import('@/views/student/exam/createExam.vue'),
          name:'EditExam',
          meta:{
            title:'创建试卷题目',
            hidden:false,
            icon:'FolderAdd'
          },
          props: true,
        },
        {
          path:'/student/exam/createlist',
            component:()=>import('@/views/student/exam/createExamList.vue'),
            name:'studentexamCreateList',
            meta:{
              title:'创建试卷',
              hidden:false,
              icon:'FolderAdd'
            },
            props: true,
          },
        {
          path:'/student/exam/end/:score',
            component:()=>import('@/views/student/exam/examEnd.vue'),
            name:'ExamEnd',
            meta:{
              title:'考试结束',
              hidden:true,
              icon:'FolderAdd'
            },
            props: true,
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
export const constantRoute2= [
  
  
  {
    path: '/teacher/course/:id',
    name: 'teacherCourseDetail',
    component:()=>import('@/views/teacher/course/CourseDetail.vue'),
    meta: {
      title: '课程详情',
      hidden: true,
      icon: 'FolderAdd'
    }
  },
  {
    path:'/teacher',
    component:()=>import("@/layout/index.vue"),
    name:'teacherlayout',
    redirect: '/teacher/home',
    meta:{
      title:'老师内容',
      hidden:false,
      icon:'FolderAdd',
    },
    children:[
      {
        path:'/teacher/home',
        component:()=>import('@/views/teacher/home/index.vue'),
        name:'teacherhome',
        meta:{
          title:'老师首页',
          hidden:false,
          icon:'FolderAdd'
        }
      },

    ]
  },
  {path:'/teacher/course',
    component:()=>import("@/layout/index.vue"),
    name:'teachercourse',
    redirect: '/teacher/course/list',
    meta:{
      title:'课程',
      hidden:false,
      icon:'FolderAdd',
    },
    children:[
      {
      path:'/teacher/course/list',
        component:()=>import('@/views/teacher/course/index.vue'),
        name:'teachercourseList',
        meta:{
          title:'课程列表',
          hidden:false,
          icon:'FolderAdd'
        }
      },
      {
        path:'/teacher/course/manage',
          component:()=>import('@/views/teacher/course/courseManage.vue'),
          name:'teachercourseManage',
          meta:{
            title:'课程管理',
            hidden:false,
            icon:'FolderAdd'
          }
        },
    ]

  },
  {path:'/teacher/notice',
    component:()=>import("@/layout/index.vue"),
    name:'teachernotice',
    redirect: '/teacher/notice/manage',
    meta:{
      title:'通知',
      hidden:false,
      icon:'FolderAdd',
    },
    children:[
      {
      path:'/teacher/notice/manage',
        component:()=>import('@/views/teacher/notice/index.vue'),
        name:'teachernoticeManage',
        meta:{
          title:'通知管理',
          hidden:false,
          icon:'FolderAdd'
        }
      }
      
    ]

  },
  {path:'/teacher/exam',
    component:()=>import("@/layout/index.vue"),
    name:'teacherexam',
    redirect: '/teacher/exam/list',
    meta:{
      title:'考试',
      hidden:false,
      icon:'FolderAdd',
    },
    children:[
      {
      path:'/teacher/exam/list',
        component:()=>import('@/views/student/exam/index.vue'),
        name:'teacherexamList',
        meta:{
          title:'待考列表',
          hidden:false,
          icon:'FolderAdd'
        }
      },
      {
        path:'/teacher/exam/create/:examId?',
          component:()=>import('@/views/student/exam/createExam.vue'),
          name:'teacherEditExam',
          meta:{
            title:'创建试卷题目',
            hidden:false,
            icon:'FolderAdd'
          },
          props: true,
        },
        {
          path:'/teacher/exam/createlist',
            component:()=>import('@/views/student/exam/createExamList.vue'),
            name:'teacherexamCreateList',
            meta:{
              title:'创建试卷',
              hidden:false,
              icon:'FolderAdd'
            },
            props: true,
          },
        {
          path:'/teacher/exam/end/:score',
            component:()=>import('@/views/student/exam/examEnd.vue'),
            name:'teacherExamEnd',
            meta:{
              title:'考试结束',
              hidden:true,
              icon:'FolderAdd'
            },
            props: true,
          },
    ]

  },
  

]
export const constantRoute3= [
  // {
  //   //主页
  //   path: "/admin",
  //   component: () => import("@/layout/index.vue"),
  //   name: "adminlayout",
  //   redirect: '/admin/home',
  //   meta:{
  //     title:'',
  //     hidden:false,
  //     icon:'',
  //   },
  //   children:[
  //     {
  //       path:"/admin/home",
  //       component:()=>import("@/views/administrator/home/index.vue"),
  //       name:'adminhome',
  //       meta:{
  //         title:'首页',
  //         hidden:false,
  //         icon:'House',
  //       }
  //     },
  //   ]
  // },
  {
    path:'/admin',
    component:()=>import("@/layout/index.vue"),
    name:'adminlayout',
    redirect: '/admin/home',
    meta:{
      title:'管理员内容',
      hidden:false,
      icon:'FolderAdd',
    },
    children:[
      {
        path:'/admin/home',
        component:()=>import('@/views/administrator/home/index.vue'),
        name:'adminhome',
        meta:{
          title:'管理员首页',
          hidden:false,
          icon:'FolderAdd'
        }
      },

    ]
  },
  {path:'/admin/course',
    component:()=>import("@/layout/index.vue"),
    name:'admincourse',
    redirect: '/admin/course/list',
    meta:{
      title:'课程',
      hidden:false,
      icon:'FolderAdd',
    },
    children:[
      {
      path:'/admin/course/list',
        component:()=>import('@/views/administrator/course/index.vue'),
        name:'admincourseList',
        meta:{
          title:'课程列表',
          hidden:false,
          icon:'FolderAdd'
        }
      },
      {
        path:'/admin/course/manage',
          component:()=>import('@/views/administrator/course/index.vue'),
          name:'admincourseManage',
          meta:{
            title:'课程管理',
            hidden:false,
            icon:'FolderAdd'
          }
        },
    ]
  },
  {path:'/admin/notice',
    component:()=>import("@/layout/index.vue"),
    name:'adminnotice',
    redirect: '/notice/manage',
    meta:{
      title:'通知',
      hidden:false,
      icon:'FolderAdd',
    },
    children:[
      {
      path:'/admin/notice/manage',
        component:()=>import('@/views/administrator/notice/index.vue'),
        name:'adminnoticeManage',
        meta:{
          title:'通知管理',
          hidden:false,
          icon:'FolderAdd'
        }
      }
      
    ]

  },


]
//对外暴露常量路由
// export const constantRoute= [
  
//   {
//     //登录
//     path: "/login",
//     component: () => import("@/views/login/index.vue"),
//     name: "login", //命名路由
//     meta:{
//       title:'登录',
//       hidden:true,//是否在菜单隐藏
//       icon:'Aim'
//     }
//   },
//   {
//     //主页
//     path: "/",
//     component: () => import("@/layout/index.vue"),
//     name: "layout",
//     redirect: '/home',
//     meta:{
//       title:'',
//       hidden:false,
//       icon:'',
//     },
//     children:[
//       {
//         path:"/home",
//         component:()=>import("@/views/home/index.vue"),
//         name:'home',
//         meta:{
//           title:'首页',
//           hidden:false,
//           icon:'House',
//         }
//       },
//     ]
//   },
//   {
//     path:'/Student',
//     component:()=>import("@/layout/index.vue"),
//     name:'layout',
//     redirect: '/Student/home',
//     meta:{
//       title:'学生内容',
//       hidden:false,
//       icon:'FolderAdd',
//     },
//     children:[
//       {
//         path:'/Student/home',
//         component:()=>import('@/views/home/index.vue'),
//         name:'Studenthome',
//         meta:{
//           title:'学生首页',
//           hidden:false,
//           icon:'FolderAdd'
//         }
//       },

//     ]
//   },
//   {path:'/course',
//     component:()=>import("@/layout/index.vue"),
//     name:'course',
//     redirect: '/course/list',
//     meta:{
//       title:'课程',
//       hidden:false,
//       icon:'FolderAdd',
//     },
//     children:[
//       {
//       path:'/course/list',
//         component:()=>import('@/views/course/index.vue'),
//         name:'courseList',
//         meta:{
//           title:'课程列表',
//           hidden:false,
//           icon:'FolderAdd'
//         }
//       },
//       {
//         path:'/course/manage',
//           component:()=>import('@/views/course/courseManage.vue'),
//           name:'courseManage',
//           meta:{
//             title:'课程管理',
//             hidden:false,
//             icon:'FolderAdd'
//           }
//         },
//     ]

//   },
//   {path:'/notice',
//     component:()=>import("@/layout/index.vue"),
//     name:'notice',
//     redirect: '/notice/manage',
//     meta:{
//       title:'通知',
//       hidden:false,
//       icon:'FolderAdd',
//     },
//     children:[
//       {
//       path:'/notice/manage',
//         component:()=>import('@/views/notice/index.vue'),
//         name:'noticeManage',
//         meta:{
//           title:'通知管理',
//           hidden:false,
//           icon:'FolderAdd'
//         }
//       }
      
//     ]

//   },
  
//   {
//     //404
//     path: "/404",
//     component: () => import("@/views/404/index.vue"),
//     name: "404",
//     meta:{
//       title:'404',
//       hidden:true,
//       icon:"CircleCloseFilled"
//     }
//   },
//   {
//     path: "/:pathMatch(.*)*",
//     redirect: "/404",
//     name: "any",
//     meta:{
//       title:'任意路由',
//       hidden:true,
//       icon:"CircleCloseFilled"
//     }
//   },
  
// ]

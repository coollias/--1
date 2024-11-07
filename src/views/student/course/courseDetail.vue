<!-- views/CourseDetail.vue -->
<template>
  <div class="course-detail-container">
    <div class="course-detail-header">
      <div class="course-detail-header-line1">
        <div class="course-detail-college">开课学院</div>
        <div class="course-detail-courseName">{{ course.cname }}</div>
      </div>
      <div class="course-detail-header-line2">
        <div class="topInfoNew-sub">
          <div>主讲教师:</div>
          <div>|</div>
          <div>课程编号：{{ courseId }}</div>
        </div>
      </div>
    </div>
    <div class="course-detail-main">
      <el-menu class="sidebar" :default-active="currentSection" @select="showSection">
        <el-sub-menu index="intro">
          <template #title><el-icon>
              <InfoFilled />
            </el-icon>课程信息</template>
          <el-menu-item index="intro-overview"><el-icon>
              <Tickets />
            </el-icon>课程概览</el-menu-item>
          <el-menu-item index="intro-schedule"><el-icon>
              <Calendar />
            </el-icon>课程安排</el-menu-item>
          <el-menu-item index="intro-inform"><el-icon>
              <Message />
            </el-icon>课程通知</el-menu-item>
        </el-sub-menu>
        <el-menu-item index="resources"><el-icon>
            <Notebook />
          </el-icon>课程资源</el-menu-item>
        <el-menu-item index="homework"><el-icon>
            <Reading />
          </el-icon>课程作业</el-menu-item>
        <el-menu-item index="test"><el-icon>
            <EditPen />
          </el-icon>课程考核</el-menu-item>
        <el-menu-item index="discussion"><el-icon>
            <Comment />
          </el-icon>课程讨论区</el-menu-item>
      </el-menu>

      <div class="content">
        <el-card>
          <h2>{{ course.name }}</h2>
          <div v-if="currentSection === 'intro-overview'">
            <p>{{description}}</p>
            <br>
            <el-button @click="change">修稿</el-button>
          </div>
          <div v-if="currentSection === 'intro-schedule'">
            这里是课程安排
          </div>
          <div v-if="currentSection === 'intro-inform'">
            这里是课程通知
          </div>
          <div v-if="currentSection === 'resources'">
            这里是课程资源
          </div>
          <div v-if="currentSection === 'homework'">
            这里是课程作业
          </div>
          <div v-if="currentSection === 'test'">
            这里是课程考核
          </div>
          <div v-if="currentSection === 'discussion'">
            这里是课程讨论区
          </div>
        </el-card>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import { useCourseStore } from '../../../store/modules/setting'
import request from '@/utils/request'
const route = useRoute();
const courseId = parseInt(route.params.id);
const course = ref({});
const courses = useCourseStore().courses;
const description=ref();
const foundCourse=ref();
// 课程资源示例
const courseResources = [
  "课程文档链接1",
  "课程文档链接2",
  "课程视频链接"
];

const currentSection = ref('intro-inform'); // 当前选中的导航项

// 显示不同的课程部分
const showSection = (section) => {
  currentSection.value = section;
};


onMounted(() => {
  //获取课程简介
const get = async () => {
  const response = await request.get(`/courses/${courseId}`);
  //  console.log(response.description);
   description.value=response.description;
   course.value=response.course;
   document.title = "课程详情 | " + response.course.cname;  // 动态设置网页标题为课程名称
  return response; // 这里返回的是服务器的响应
};
get();
console.log(description);
  // console.log('课程ID:', courseId);
  // console.log('课程列表:', courses);
   console.log(foundCourse);
  // const foundCourse = courses.find(c => c.id == courseId);
  // if (1) {
  //   course.value = foundCourse.value;
  //   document.title = "课程详情 | " + foundCourse.value.cname;  // 动态设置网页标题为课程名称
  // } else {
  //   course.value = { name: '课程未找到', time: '', description: '', img: '' };
  // }
});

// const change=async()=>{
//   const response=await request.post(`/tc/courses/${courseId}`)
// }

</script>
<style scoped>
.course-detail-container {
  height: 100vh;
}

.course-detail-header {
  width: 100%;
  height: 80px;
  background-color: #095aa1;
  background-image: url(../../assets/images/login_back.jpg);
  background-size: 100% 100%;
  background-repeat: no-repeat;
  color: #fff;
  box-shadow: 0 0 7px 0 rgb(0 0 0 / 50%);
}

.course-detail-header-line1 {
  width: 100%;
  height: 50px;
  padding-right: 50px;
  padding-left: 150px;
}

.course-detail-header-line2 {
  width: 100%;
  height: 30px;
  padding-right: 50px;
  padding-left: 150px;
}

.topInfoNew-sub {
  display: inline-block;
  height: 30px;
  border-top-left-radius: 5px;
  border-top-right-radius: 5px;
  background-color: #5776ac;
}

.topInfoNew-sub div {
  display: inline-block;
  height: 30px;
  line-height: 30px;
  color: #fff;
  margin: 0 10px;
}

.course-detail-college {
  float: right;
  width: 300px;
  height: 50px;
  text-align: right;
  line-height: 50px;
  font-size: 20px;
  font-weight: bold;
  color: #fff;
  padding-right: 60px;
}

.course-detail-courseName {
  float: left;
  width: 300px;
  height: 50px;
  text-align: left;
  line-height: 50px;
  font-size: 24px;
  font-weight: bold;
  color: #fff;
  padding-right: 20px;
}

.course-detail-main {
  height: auto;
  display: flex;
}

.sidebar {
  height: 625px;
  /* 80px 是头部的高度 */
  overflow-y: auto;
  /* 允许垂直滚动 */
  margin-top: 20px;
  width: 200px;
  /* border-right: 1px solid #464646; */
  background-color: #ffffff;
  padding: 10px;
  box-shadow: 0 0 7px 0 rgba(69, 69, 69, 0.5);
}


.content {
  padding: 20px;
  flex: 1;
  /* 让内容区域填满剩余空间 */
}
</style>
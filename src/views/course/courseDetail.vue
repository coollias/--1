<!-- views/CourseDetail.vue -->
<template>
    <div>
        <p>课程{{ courseId }}</p>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  import { useRoute } from 'vue-router';

  
  const route = useRoute();
  const courseId = parseInt(route.params.id);
//   const course = ref({});
  
//   onMounted(() => {
//     const foundCourse = courses.find(c => c.id === courseId);
//     if (foundCourse) {
//       course.value = foundCourse;
//     } else {
//       course.value = { name: '课程未找到', time: '', description: '', img: '' };
//     }
//   });
  </script>
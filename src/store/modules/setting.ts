//小仓库：layout配置仓库
import { defineStore } from "pinia";

let useLayOutSettingStore =defineStore('SettingStore',{
  state:()=>{
    return {
      fold:false,//用于控制菜单折叠
    }
  }
})

import { ref } from 'vue';
import { courseList} from '@/api/user';
const courses = ref<any[]>([]);
const get=async()=>{
  let result=await courseList();
  // console.log(result);
  courses.value=result as any[];

}
get();
export const useCourseStore = defineStore('CourseStore', () => {

  
  return { courses };
});

export default useLayOutSettingStore;
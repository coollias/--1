import { defineStore } from "pinia";
import { reqLogin,reqUserInfo } from "@/api/user";

import type { UserState } from "./types/type";
import { GET_TOKEN, SET_TOKEN } from "@/utils/token";
import { constantRoute } from "@/router/routes";

let useUserStore=defineStore('User',{
  //小仓库存储数据
  state:():UserState=>{
    return {
      token:GET_TOKEN(),//用户标识
      menuRoutes:constantRoute,//仓库存储生成菜单需要的数组
      username:'',
      avatar:''
    }
  },
  //处理逻辑
  actions:{
    //用户登录
    async userLogin(data:any){
      let result:any = await reqLogin(data);
      console.log(result);
      //console.log(result);
      if(result.code==200){
        this.token=(result.data as string);
        SET_TOKEN((result.data as string))
        //console.log('success');
        return 'ok';
      }
      else{
        return Promise.reject(new Error(result.data));
      }
    },
    //获取用户信息
    async userInfo(){
      let result=await reqUserInfo();
      console.log(result);
      if(result.code==200){
        this.username=result.data.name;
        this.avatar=result.data.avatar;
      }else{
          return Promise.reject(new Error(result.message));
      }
    }
  },
  getters:{

  }
})

export default useUserStore;
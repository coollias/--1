import { defineStore } from "pinia";
import { reqLogin,reqUserInfo,reqUserIdentity } from "@/api/user";

import type { UserState } from "./types/type";
import { GET_IDENTITY, GET_TOKEN, SET_IDENTITY, SET_TOKEN } from "@/utils/token";
import {  constantRoute1 ,constantRoute2,constantRoute3} from "@/router/routes";

let useUserStore=defineStore('User',{
  //小仓库存储数据
  state:():UserState=>{
    return {
      token:GET_TOKEN(),//用户标识
      menuRoutes1:constantRoute1,//仓库存储生成菜单需要的数组
      menuRoutes2:constantRoute2,
      menuRoutes3:constantRoute3,
     
      username:'',
      avatar:'',
      identity:GET_IDENTITY(),
    }
  },
  //处理逻辑
  actions:{
    //用户登录
    async userLogin(data:any){
      let result:any = await reqLogin(data);
      console.log(result);
      //console.log(result);
      if(result.code==0){
        
        this.token=(result.data as string);
        SET_TOKEN((result.data as string));

        // 登录成功后调用 userIdentity,获取用户身份
       await this.userIdentity();
        //console.log('success');
        return 'ok';
      }
      else{
        return Promise.reject(new Error(result.message));
      }
    },
    // //获取用户信息
    // async userInfo(){
    //   let result=await reqUserInfo();
    //   console.log(result);
    //   if(result.code==200){
    //     this.username=result.data.name;
    //     this.avatar=result.data.avatar;
    //   }else{
    //       return Promise.reject(new Error(result.message));
    //   }
    // },
     //获取用户身份
     async userIdentity(){
      let result=await reqUserIdentity();
      console.log(result);
      if(result.code==0){
        
        this.identity=(result.data as string);
        SET_IDENTITY((result.data as string));
      
        console.log('Identity assigned:', this.identity);
      }else{
          return Promise.reject(new Error(result.message));
      }
    }
  },
  getters:{

  }
})

export default useUserStore;
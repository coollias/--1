//用户相关接口
 import request from '@/utils/request'
// import type { loginForm,loginResponseData,userResponseData } from './type';

// enum API{
//   LOGIN_URL="/user/login",
//   USERINFO_URL="/user/info"
// }

// export const reqLogin =(data:loginForm)=>request.post<any,loginResponseData>(API.LOGIN_URL,data);

// export const reqUserInfo=()=>request.get<any,userResponseData>(API.USERINFO_URL);

//请求地址
enum API{
  LOGIN_URL="/admin/acl/index/login",
  USERINFO_URL="/admin/acl/index/info",
  AWARD_POST="/reward/submit",
  AWARD_GET="/reward/info"
}
//登录接口
export const reqLogin=(data:any)=>request.post<any,any>(API.LOGIN_URL,data);
//获取用户信息
export const reqUserInfo=()=>request.get<any,any>(API.USERINFO_URL);
export const awardInfo=()=>request.get<any,any>(API.AWARD_GET);
export const awardPost=(data:any)=>request.post<any>(API.AWARD_POST,data);
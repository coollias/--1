//封装本地存储读取数据方法
//本地存储数据
export const SET_TOKEN=(token:string)=>{
  localStorage.setItem("TOKEN",token);
}
//本地获取数据
export const GET_TOKEN=()=>{
  return localStorage.getItem('TOKEN');
}
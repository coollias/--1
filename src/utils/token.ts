//封装本地存储读取数据方法
//本地存储数据
export const SET_TOKEN=(token:string)=>{
  localStorage.setItem("TOKEN",token);
}
//本地获取数据
export const GET_TOKEN=()=>{
  return localStorage.getItem('TOKEN');
}

export const SET_IDENTITY=(token:string)=>{
  localStorage.setItem("IDENTITY",token);
}
//本地获取数据
export const GET_IDENTITY=()=>{
  return localStorage.getItem('IDENTITY');
}
//axios 二次封装，使用请求以及响应拦截器
import axios from "axios";
import { ElMessage } from "element-plus";
import userUserStore from '@/store/modules/user';
let request = axios.create({
  baseURL: "/api",
  timeout: 5000,
});
//请求拦截
request.interceptors.request.use((config) => {
  let userStore=userUserStore();
  if(userStore.token){
    config.headers.token=userStore.token;
  }
  return config;
});
//响应拦截
request.interceptors.response.use(
  (response) => {
    return response.data;
  },
  (error) => {
    let message = "";
    let status = error.response.status;
    switch (status) {
      case 401:
        message = "token过期";
        break;
      case 403:
        message = "无权访问";
        break;
      case 404:
        message = "请求地址错误";
        break;
      case 500:
        message = "服务器错误";
        break;
      default:
        message = "网络问题";
        break;
    }
    ElMessage({
      type: "error",
      message,
    });

    return Promise.reject(error);
  }
);
//对外暴露
export default request;
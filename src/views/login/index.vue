<template>
  <div class="login_container">
    <el-row>
      <el-col :span="12" :xs="0"></el-col>
      <el-col :span="12" :xs="24">
        <el-form class="login_form" :model="loginForm" :rules="rules" ref="loginforms">
          <h1>Hello</h1>
          <h2>欢迎学生登录</h2>
          <el-form-item prop="username">
            <el-input
              v-model="loginForm.username"
              placeholder="请输入用户名"
              :prefix-icon='User'
            />
          </el-form-item>
          <el-form-item prop="password">
            <el-input
              v-model="loginForm.password"
              type="password"
              placeholder="请输入用户密码"
              show-password
              :prefix-icon="Lock"
            />
          </el-form-item>
          <el-form-item>
            <el-button :loading="loading" type="primary" size="default" @click="login">登录</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>

<script setup lang="ts">
import { User,Lock,  } from "@element-plus/icons-vue";
import { reactive,ref } from "vue";
import userUserStore from '@/store/modules/user'
import { useRouter } from "vue-router";
import { ElNotification } from "element-plus";


let $router=useRouter();
let useStore=userUserStore();
const loginForm=reactive({username:'admin',password:'111111'});
const loading=ref(false);
let loginforms=ref();

const login=async()=>{
  await loginforms.value.validate();
  //开始加载的效果
  loading.value=true;
  try {
    await useStore.userLogin(loginForm);
    $router.push('/');
    ElNotification({
      type:'success',
      message:'登陆成功'
    })
    loading.value=false;
  } catch (error) {
    loading.value=false;
    ElNotification({
      type:'error',
      message:(error as Error).message
    })
  }
    
}

const rules={
  username:[
    {required:true, message:'请输入内容',trigger:'blur'},
    {required:true, min:5,max:10,message:'5-10',trigger:'blur'}
  ],
  password:[],
}
</script>

<style scoped lang="scss">
.login_container {
  width: 100%;
  height: 100vh;
  background: url("@/assets/images/login_back.jpg") no-repeat;
  background-size: cover;
}

.login_form {
  position: relative;
  width: 80%;
  top: 40vh;
  //background: url("@/assets/images/mao.jpg");
  background-size: cover;
  //padding: 40px;
  h1 {
    color: white;
    font-size: 40px;
  }
  h2 {
    font-size: 20px;
    color: white;
    margin: 20px 0px;
  }
}
</style>

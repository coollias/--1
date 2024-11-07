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
              :prefix-icon="User"
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
import { User, Lock } from "@element-plus/icons-vue";
import { ref } from "vue";
import userUserStore from '@/store/modules/user';
import { useRouter } from "vue-router";
import { ElNotification } from "element-plus";

const $router = useRouter();
const useStore = userUserStore();
const loginForm = ref({ username: '123456', password: '123456' });
const loading = ref(false);
const loginforms = ref();

const login = async () => {
  await loginforms.value.validate();
  loading.value = true;
  
  try {
    await useStore.userLogin(loginForm.value);
    const userIdentity = useStore.identity; // 获取用户身份

    // 根据身份跳转
    if (userIdentity === "1") {
      $router.push('/student'); // 学生主页
    } else if (userIdentity === "2") {
      $router.push('/teacher'); // 教师主页
    } else if (userIdentity === "3") {
      $router.push('/admin'); // 管理员主页
    } else {
      $router.push('/'); // 默认主页
    }

    ElNotification({
      type: 'success',
      message: '登陆成功'
    });
  } catch (error) {
    ElNotification({
      type: 'error',
      message: (error as Error).message
    });
  } finally {
    loading.value = false;
  }
}

const rules = {
  username: [
    { required: true, message: '请输入内容', trigger: 'blur' },
    { min: 5, max: 10, message: '5-10个字符', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' }
  ],
};
</script>

<style scoped lang="scss">
.login_container {
  width: 100%;
  height: 100vh;
  background: url("@/assets/images/login_back.jpg") no-repeat center center;
  background-size: cover;
}

.login_form {
  position: relative;
  width: 80%;
  top: 40vh;
  
  h1 {
    color: white;
    font-size: 40px;
  }
  
  h2 {
    font-size: 20px;
    color: white;
    margin: 20px 0;
  }
}
</style>

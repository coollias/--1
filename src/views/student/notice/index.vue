<template>
    <div>通知收发测试</div>
    <br />
    <div>
      <el-input v-model="inputValue" style="width: 240px" placeholder="请输入你要发送的内容" />
      <br />
      <!-- <el-button @click="init">连接</el-button> -->
      <el-button @click="sendMsg">发送</el-button>
      <el-button @click="close">关闭</el-button>
    </div>
  
    <el-table :data="users" @selection-change="handleSelectionChange" style="width: 100%">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="用户ID" width="180"></el-table-column>
      <el-table-column label="用户身份" width="180">
        <template #default="{ row }">
          <span>{{ getUserIdentity(row.identity) }}</span>
        </template>
      </el-table-column>
    </el-table>
  </template>
  
  <script setup>
  import { ref,onMounted  } from 'vue';
  import { ElNotification } from 'element-plus'; // 引入 Element Plus 通知
  import { reqUserInfo, UserList } from '@/api/user';
  
  let inputValue = ref('');
  let maxTime = 3; // 重连最大的连接次数
  let tryTime = 0; // 重连后出错的次数
  let reconnentTimer; // 重连定时器
  let client;
  let headerBeatTimer; // 心跳定时器
  const inputid = ref('');
  const inputtoid = ref('');
  let isClosed = false; // 用于标记连接是否已关闭
  
  const sid = ref();
  const get = async () => {
  let result = await reqUserInfo();
  sid.value = result.data;
  initWebSocket(); // 确保在获取到 sid 后再初始化 WebSocket 连接
};
  get();
  
  const users = ref([]);
  
  const getUsers = async () => {
    let result = await UserList();
    users.value = result; // 假设返回的数据是用户数组
  };
  getUsers();
  
  const selectedUsers = ref([]); // 存储选中的用户
  
  const initWebSocket = () => {
    client = new WebSocket(`ws://localhost:8080/websocket/${sid.value}`); // 修改为你的后端 WebSocket 地址
  
    client.onopen = () => {
      clearTimeout(reconnentTimer);
      headerBeat(); // 连接上后就开启心跳检测
      console.log('连接上了');
    };
  
    client.onmessage = (msg) => {
      const data = JSON.parse(msg.data);
      if (data.type == "note") {
        // 显示 Element Plus 通知
        ElNotification({
          title: '新消息',
          message: data.msg, // 假设消息在data.msg中
          type: 'info',
        });
      }
    };
  
    client.onclose = () => {
      if (!isClosed) { // 只有在未调用 close 时才重新连接
        reconnect();
        console.log('close');
      }
    };
  
    client.onerror = () => {
      if (!isClosed) { // 只有在未调用 close 时才重新连接
        tryTime += 1;
        reconnect();
        console.log('error');
      }
    }
  };
  
  const reconnect = () => {
    clearTimeout(reconnentTimer);
    if (tryTime > maxTime) { // 重连次数超过3次就不再重连
      alert('重连次数超过3次，请联系管理员！');
      clearTimeout(reconnentTimer);
      clearInterval(headerBeatTimer);
      return;
    }
    reconnentTimer = setTimeout(() => {
      initWebSocket();
    }, 2000);
  };
  
  const headerBeat = () => {
    clearInterval(headerBeatTimer);
    headerBeatTimer = setInterval(() => {
      client.send(JSON.stringify({
        id: sid.value,
        type: 'headerBeat',
        msg: 'test',
      }));
    }, 3000);
  };
  
  const handleSelectionChange = (selection) => {
    selectedUsers.value = selection; // 更新选中的用户
  };
  
  const sendMsg = () => {
    selectedUsers.value.forEach(user => {
      client.send(JSON.stringify({
        id: user.id,
        type: 'note',
        msg: inputValue.value,
      }));
    });
  };
  
//   const init = () => {
//     // 初始化 WebSocket 连接
//     initWebSocket();
//   };
//    init();
// onMounted(() => {

//     get();
//     getUsers();
//     console.log(sid)
//   initWebSocket(); // 在组件挂载时连接 WebSocket
// });
  const close = () => {
    if (client) {
      client.close(); // 关闭 WebSocket 连接
      console.log('WebSocket 连接已关闭');
      clearInterval(headerBeatTimer); // 清除心跳定时器
      isClosed = true; // 设置为已关闭状态
      client = null; // 清空 client 变量
    } else {
      console.log('没有可关闭的 WebSocket 连接');
    }
  };
  
  // 根据用户身份 ID 返回身份描述
  const getUserIdentity = (identity) => {
    return identity === 1 ? '学生' : identity === 2 ? '老师' : '未知身份';
  };
  
  </script>
  
  <style scoped></style>
  